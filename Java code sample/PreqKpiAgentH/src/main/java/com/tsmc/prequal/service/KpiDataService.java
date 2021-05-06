package com.tsmc.prequal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.tsmc.prequal.dao.PreBatchStatusDao;
import com.tsmc.prequal.dao.PreqMonitorResultDao;
import com.tsmc.prequal.data.model.dto.MonitorCriteriaVo;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.data.model.po.RawmatPreBatchStatus;
import com.tsmc.prequal.service.dto.IKpiJobAction;
import com.tsmc.prequal.service.dto.KpiCheckResultVo;
import com.tsmc.prequal.utils.DateUtils;
import com.tsmc.prequal.utils.KpiDataSubjectEnum;
import com.tsmc.prequal.utils.StringUtil;

/// @Component is required for scheduling
@Component
public class KpiDataService {

	private Logger LOG = LoggerFactory.getLogger(KpiDataService.class);

	@Value("${kpiAgent.fabName}")
	private String FabName;

	@Value("${kpiAgent.dataSubject}")
	private KpiDataSubjectEnum DataSubject; /// FAC, SPC, iDS

	@Autowired
	private PreBatchStatusDao preqCaseDao;

	@Autowired
	private PreqMonitorResultDao preqMonRsltDao;

	@Scheduled(cron = "${kpiSchedule.cron.expression}")
	public void scheduledTasks() throws JsonProcessingException {

		/// DateUtils.getCurTimeString()
		String curMehtod = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOG.info(String.format("[%s] executed at %s, fabName: %s, dataSubject: %s.", curMehtod, LocalDateTime.now(),
				this.FabName, this.DataSubject));

		// ObjectMapper jsonObjectMapper = new ObjectMapper();

		goInitialTest();

		Map<String, RawmatPreBatchStatus> kpiCaseMap = new HashMap<String, RawmatPreBatchStatus>();
		kpiCaseMap = getEarliestCases(this.DataSubject);

		if (kpiCaseMap == null || kpiCaseMap.size() < 1) {
			LOG.info(String.format("[%s] Can't find any PreQual KPI cases...", curMehtod));
			return;
		}

		for (Map.Entry<String, RawmatPreBatchStatus> entry : kpiCaseMap.entrySet()) {

			try {

				LOG.info(String.format("Start to process caseId: %s, value:%s", entry.getKey(),
						StringUtil.writeAsJson(entry.getValue())));

				RawmatPreBatchStatus curCaseObj = entry.getValue();

				/// TODO: Think to put execResult as response object.
				doKpiCheck(curCaseObj);

			} catch (JsonProcessingException e) { /// JsonProcessingException
				LOG.info(String.format("[%s] Exception: %s, \n%s", e.getMessage(), e.getStackTrace()));
			} catch (Exception ex) {
				LOG.info(String.format("[%s] Exception: %s, \n%s", ex.getMessage(), ex.getStackTrace()));
			} catch (Error err) {
				LOG.info(String.format("[%s] Error: %s, \n%s", err.getMessage(), err.getStackTrace()));
			}

		}

	}

	public void doKpiCheck(RawmatPreBatchStatus _curKpiBatch) {

		// String procDataSubjString = this.DataSubject.name();

		List<RawmatMonitorResult> kpiJobList = new ArrayList<RawmatMonitorResult>();
		kpiJobList = findAllMonitorJobList(_curKpiBatch.getCaseId(), this.FabName, this.DataSubject.name());

		Map<String, KpiCheckResultVo> kpiChkRsltMap = new HashMap<String, KpiCheckResultVo>();

		for (Iterator<RawmatMonitorResult> iterator = kpiJobList.iterator(); iterator.hasNext();) {

			RawmatMonitorResult curMonJob = iterator.next();
			IKpiJobAction curKpiHandler = null;

			/// TODO: getCurJobKey
			String curKpiJobKey = "";

			curMonJob.getMonitorCriteria();
			curMonJob.getResultRaw();
			curMonJob.getResultPath();
			curMonJob.getExecHist();

			KpiCheckResultVo kpiChkRslt = new KpiCheckResultVo(curMonJob.getJobStatus());

			curKpiHandler = initialKpiController(this.DataSubject);

			switch (curMonJob.getJobStatus()) {
			case "2":
				kpiChkRslt = curKpiHandler.getKpiCriteria(this.DataSubject, curMonJob);
			case "3":
			case "6":
			case "8":
				kpiChkRslt = curKpiHandler.doKpiCheck(this.DataSubject, curMonJob);
				break;

			default:

				break;
			}

			if (!kpiChkRslt.getOldStatus().equals(kpiChkRslt.getNewStatus())) {
				
				if (!kpiChkRsltMap.containsKey(curKpiJobKey)) {
					kpiChkRsltMap.put(curKpiJobKey, kpiChkRslt); 
				}
				else {
					kpiChkRsltMap.replace(curKpiJobKey, kpiChkRslt); 	
				}

			}

		}

	}

	private IKpiJobAction initialKpiController(KpiDataSubjectEnum _dataSubject) {

		IKpiJobAction trgtMonitor = null;
		switch (_dataSubject) {
		case FAC:
			trgtMonitor = new FacKpiJobAction();
			break;

		default:
			break;
		}

		return trgtMonitor;
	}

	private Map<String, RawmatPreBatchStatus> getEarliestCases(KpiDataSubjectEnum _dataSubject) {
		
		List<Integer> targetJobStatusLst = Arrays.asList(0);
		List<String> targetDataTypes = Arrays.asList("FAC"); 
		List<String> topNCaseIdList = preqMonRsltDao.findDistTopNCaseIds(targetJobStatusLst, targetDataTypes, 5); 
		
		LOG.info(String.format(">> topNCaseIdList.size(): %s", topNCaseIdList.size()));
		LOG.info(String.format(">> topNCaseIdList= %s", StringUtils.join(topNCaseIdList, ",")));
		
		List<RawmatMonitorResult> topNMonitorJobs = preqMonRsltDao.findTopNJobsByStatusAndDataType(targetJobStatusLst, targetDataTypes, 5); 
		LOG.info(String.format(">> topNMonitorJobs.size(): %s", topNMonitorJobs.size()));
		
		return null;
	}

	/// Get all Jobs under this caseId and current Kpi-MeasDataType
	private List<RawmatMonitorResult> findAllMonitorJobList(Long _caseId, String _fabName, String _measDataType) {

		List<RawmatMonitorResult> _rtnJobList = preqMonRsltDao.findAllByCaseIdAndDataType(_caseId, _measDataType);

		return _rtnJobList;
		// return Collections.singletonMap("username1", "password1");
	}

	private void goInitialTest() throws JsonProcessingException {
		
		MonitorCriteriaVo newCri = new MonitorCriteriaVo(); 
		newCri.setFabName("FAB15B");
		newCri.setMatNo("L123480");
		String newMonCriJson = MonitorCriteriaVo.writeAsJson(newCri); 
		
		RawmatMonitorResult newMonRslt = new RawmatMonitorResult(); 
		newMonRslt.setCaseId(1);
		newMonRslt.setFabName("FAB15B");
		newMonRslt.setMeasDataType("FAC");
		newMonRslt.setMonitorCriteria(newMonCriJson);
		
		//preqMonRsltDao.insertMonitorResult(newMonRslt);
		
		List<RawmatMonitorResult> curMonJobsList = preqMonRsltDao.findAllByCaseId(1); 
		for (Iterator<RawmatMonitorResult> iterator = curMonJobsList.iterator(); iterator.hasNext();) {
			RawmatMonitorResult curMonJob = (RawmatMonitorResult) iterator.next();
			LOG.info(String.format("[%s] curMonCriteria: %s, fabName:%s, measDataType: %s", 
					curMonJob.getJobId(), curMonJob.getMonitorCriteria(), curMonJob.getFabName(), curMonJob.getMeasDataType()));
		}
		
		getEarliestCases(this.DataSubject); 
		
		
		/// TODO: Change to Java get method.name
		String curMehtod = "goInitialTest";
		LOG.info(String.format("[%s] executed at %s", curMehtod, LocalDateTime.now()));

		MonitorCriteriaVo tmp = new MonitorCriteriaVo();
		tmp.setMatNo("L144568");
		LOG.info(String.format("[%s] StringUtil.writeAsJson: %s", curMehtod, StringUtil.writeAsJson(tmp)));

		String tmpJson = "{\"fabName\":null,\"matPhaseId\":null,\"plantCode\":null,\"matNo\":\"L144568\",\"batchId\":null,\"contrId\":null,\"matChangeTime\":null,\"matUsedTime\":null,\"ntAccount\":\"YCWENGC\",\"chartTitle\":null,\"criteria\":null}";
		tmp = (MonitorCriteriaVo) StringUtil.readFromJson(tmpJson, MonitorCriteriaVo.class);
		LOG.info(String.format("[%s] StringUtil.readFromJson: %s, %s", curMehtod, tmp.getMatNo(), tmp.getNtAccount()));

	}
}
