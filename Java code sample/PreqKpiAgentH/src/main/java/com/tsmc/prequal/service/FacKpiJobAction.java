package com.tsmc.prequal.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tsmc.prequal.data.model.dto.MatchingResultVo;
import com.tsmc.prequal.data.model.dto.MonitorCriteriaVo;
import com.tsmc.prequal.data.model.dto.ResultPathVo;
import com.tsmc.prequal.data.model.dto.TargetPointVo;
import com.tsmc.prequal.data.model.po.RawmatMonitorResult;
import com.tsmc.prequal.service.dto.IKpiJobAction;
import com.tsmc.prequal.service.dto.KpiCheckResultVo;
import com.tsmc.prequal.utils.KpiDataSubjectEnum;

import com.tsmc.prequal.utils.StringUtil; 

public class FacKpiJobAction implements IKpiJobAction {

	private Logger LOG = LoggerFactory.getLogger(FacKpiJobAction.class);

	
	public FacKpiJobAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public KpiCheckResultVo getKpiCriteria(KpiDataSubjectEnum dataSubject, RawmatMonitorResult curMonJob) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public KpiCheckResultVo doKpiCheck(KpiDataSubjectEnum dataSubject, RawmatMonitorResult curMonJob) {
		
		KpiCheckResultVo rtnChkRslt = new KpiCheckResultVo(curMonJob.getJobStatus()); 
		//rtnChkRslt.setOldStatus(curMonJob.getJobStatus());
				
		List<TargetPointVo> tmpFacPointLst = getFacRawData(); /// TODO: Implement 
		MonitorCriteriaVo curJobCriteria = null; 
		ResultPathVo rsltPathObj = null; 
		
		try {
			curJobCriteria = MonitorCriteriaVo.readFromJson(curMonJob.getMonitorCriteria());
			rsltPathObj = ResultPathVo.readFromJson(curMonJob.getResultPath());
			
			String tgStartDate = rsltPathObj.getTargetStartDate(); 
		

			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			LOG.info(String.format("%s", e));
		} 
		
		if(StringUtils.isNotEmpty(curMonJob.getMonitorResult()))
		{ 
			try {
				MatchingResultVo matchRslt = (MatchingResultVo)StringUtil.readFromJson(curMonJob.getMonitorResult(), MatchingResultVo.class);
				
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				LOG.info(String.format("%s", e));
			} 
			
		}
		
		return rtnChkRslt;
	}
	
	
	private List<TargetPointVo> getFacRawData() {
		// TODO Auto-generated method stub
		return null;
	}

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}


}
