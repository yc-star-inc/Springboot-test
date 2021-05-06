package com.tsmc.prequal.data.model.po;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsmc.prequal.data.model.dto.MonitorCriteriaVo;
import com.tsmc.prequal.utils.DateUtils;
import com.tsmc.prequal.utils.StringUtil;

//import com.fasterxml.jackson.annotation.JsonFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RawmatMonitorResult {

	public static String writeAsJson(RawmatMonitorResult _curObj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(DateUtils.DateTimeFormat);
		return objectMapper.writeValueAsString(_curObj);
	}

	public static RawmatMonitorResult readFromJson(String _jsonString) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		RawmatMonitorResult rtnObj = objectMapper.readValue(_jsonString, RawmatMonitorResult.class);
		return rtnObj;
	}

	@Column(name = "JOB_ID")
	private Integer jobId;

	@Column(name = "CASE_ID")
	private Integer caseId;

	@Column(name = "FAB_NAME")
	private String fabName;

	@Column(name = "PHASE_ID")
	private String phaseId;

	@Column(name = "SECT_CD")
	private String sectCode;

	@Column(name = "MONITOR_CRI")
	private byte[] monitorCriBytes;
	private String monitorCriteria;

	@Column(name = "RESULT_RAW")
	private byte[] resultRawBytes;
	private String resultRaw;

	@Column(name = "RESULT_PATH")
	private byte[] resultPathBytes;
	private String resultPath;

	@Column(name = "JOB_STATUS")
	private String jobStatus;

	@Column(name = "SUBMIT_FA_RESULT")
	private byte[] submitFaResultBytes;
	private String submitFaResult;

	@Column(name = "MONITOR_RESULT")
	private byte[] monitorResultBytes;
	private String monitorResult;

	@Column(name = "MEAS_DATA_TYPE")
	private String measDataType;

	// @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_CHK_DT")
	private Timestamp lastCheckTime;

	@Column(name = "MAIN_CRTRN")
	private String mainCriteria;

	@Column(name = "SUB_CRTRN")
	private String subCriteria;

	@Column(name = "PARM")
	private String parm;

	@Column(name = "EXEC_HIST")
	private byte[] execHistBytes;
	private String execHist;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getFabName() {
		return fabName;
	}

	public void setFabName(String fabName) {
		this.fabName = fabName;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public String getSectCode() {
		return sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	public String getExecHist() {
		return execHist;
	}

	public void setExecHist(String execHist) {
		this.execHist = execHist;
		if (StringUtils.isNotEmpty(execHist)) {
			this.execHistBytes = execHist.getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] getExecHistBytes() {
		return execHistBytes;
	}

	public void setExecHistBytes(byte[] execHistBytes) {
		this.execHistBytes = execHistBytes;
		if (execHistBytes != null)
			execHist = new String(execHistBytes, StandardCharsets.UTF_8);
	}

	public String getMonitorCriteria() {
		return this.monitorCriteria;
	}

	public void setMonitorCriteria(String monitorCriteria) {
		this.monitorCriteria = monitorCriteria;
		if (StringUtils.isNotEmpty(monitorCriteria)) {
			this.monitorCriBytes = monitorCriteria.getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] getMonitorCriBytes() {
		return monitorCriBytes;
	}

	public void setMonitorCriBytes(byte[] monitorCriBytes) {
		this.monitorCriBytes = monitorCriBytes;
		if (monitorCriBytes != null)
			monitorCriteria = new String(monitorCriBytes, StandardCharsets.UTF_8);
	}

	public byte[] getResultRawBytes() {
		return resultRawBytes;
	}

	public void setResultRawBytes(byte[] resultRawBytes) {
		this.resultRawBytes = resultRawBytes;
		if (resultRawBytes != null)
			this.resultRaw = new String(resultRawBytes, StandardCharsets.UTF_8);
	}

	public String getResultRaw() {
		return resultRaw;
	}

	public void setResultRaw(String resultRaw) {
		this.resultRaw = resultRaw;
		if (StringUtils.isNotEmpty(resultRaw)) {
			this.resultRawBytes = resultRaw.getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] getResultPathBytes() {
		return resultPathBytes;
	}

	public void setResultPathBytes(byte[] resultPathBytes) {
		this.resultPathBytes = resultPathBytes;
		if (resultPathBytes != null)
			this.resultPath = new String(resultPathBytes, StandardCharsets.UTF_8);
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
		if (StringUtils.isNotEmpty(resultPath)) {
			this.resultPathBytes = resultPath.getBytes(StandardCharsets.UTF_8);
		}
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public byte[] getSubmitFaResultBytes() {
		return submitFaResultBytes;
	}

	public void setSubmitFaResultBytes(byte[] submitFaResultBytes) {
		this.submitFaResultBytes = submitFaResultBytes;
		if (submitFaResultBytes != null)
			this.submitFaResult = new String(submitFaResultBytes, StandardCharsets.UTF_8);
	}

	public String getSubmitFaResult() {
		return submitFaResult;
	}

	public void setSubmitFaResult(String submitFaResult) {
		this.submitFaResult = submitFaResult;
		if (StringUtils.isNotEmpty(submitFaResult)) {
			this.submitFaResultBytes = submitFaResult.getBytes(StandardCharsets.UTF_8);
		}
	}

	public byte[] getMonitorResultBytes() {
		return monitorResultBytes;
	}

	public void setMonitorResultBytes(byte[] monitorResultBytes) {
		this.monitorResultBytes = monitorResultBytes;
		if (monitorResultBytes != null)
			this.monitorResult = new String(monitorResultBytes, StandardCharsets.UTF_8);
	}

	public String getMonitorResult() {
		return monitorResult;
	}

	public void setMonitorResult(String monitorResult) {
		this.monitorResult = monitorResult;
		if (StringUtils.isNotEmpty(monitorResult)) {
			this.monitorResultBytes = monitorResult.getBytes(StandardCharsets.UTF_8);
		}
	}

	public String getMeasDataType() {
		return measDataType;
	}

	public void setMeasDataType(String measDataType) {
		this.measDataType = measDataType;
	}

	public Timestamp getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Timestamp lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getMainCriteria() {
		return mainCriteria;
	}

	public void setMainCriteria(String mainCriteria) {
		this.mainCriteria = mainCriteria;
	}

	public String getSubCriteria() {
		return subCriteria;
	}

	public void setSubCriteria(String subCriteria) {
		this.subCriteria = subCriteria;
	}

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

}
