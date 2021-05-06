package com.tsmc.prequal.data.model.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@JsonIgnore 
//NonSerializeableClass obj;

public class RawmatPreBatchStatus implements Serializable {
	
	@Column(name="CASE_ID")
	private long caseId;
	
	@Column(name="FAB_NAME")
	private String fabName;
	
	private String FAB_CD;
	
	@Column(name="TOOL_ID")
	private String toolId;
	
	private String LOOP_ID;
	private String PORT;
	
	@Column(name="MAT_NO")
	private String matNo;
	
	@Column(name="BATCH_ID")
	private String batchId;
	
	@Column(name="CONTR_ID")
	private String contrId;
	
	@Column(name="MAT_CHANGE_DT")
	private Timestamp matChangeTime;
	
	private Double RMNDR_AMOUNT;
	
	@Column(name="CASE_STATUS")
	private int caseStatus;
	
	@Column(name="LAST_CHK_DT")
	private Timestamp lastCheckTime;
	
	private int CONFIRM_LOT_COUNT;
	private String LEADING_LOT;
	private String CONFIRM_LOT_LIST;
	private String STEP_ID;
	private String STEP_ID_LIST;
	private String IS_PIRUN;
	private int RMNDR_CHK_TIME;
	
	@Column(name="MAT_NAME")
	private String matName;
	
	private String PART_ID;
	
	@Column(name="CHECK_TYPE")
	private String checkType;
	
	private String PHASE_ID;
	
	@Column(name="UPDATE_SYSTEM")
	private String updateSystem;
	
	private Long LATEST_CASE;
	private String REPORT_OBJECT_ID;
	
	@Column(name="MAIL_LIST")
	private String mailList;
	
	@Column(name="MONITOR_SECTION")
	private String monitorSection;
	
	@Column(name="PREQ_RESULT")
	private String preqResult;
	
	@Column(name="SPC_MATCH_STATUS")
	private String spcMatchStatus;
	
	@Column(name="FAB_PLANT_CD")
	private String fabPlantCode;
	
	private String PREQ_USER;
	private String VENDOR_CD;
	private String PREQ_FORM_NO;
	private String CONFIRM_LOT_TOOL;
	private String MONITOR_SEC_NAME;
	private String FAC_TOOL_TYPE;
	private String TANK_ID;
	private Timestamp TANK_SWITCH_DT;
	
	@Column(name="MAT_USED_DT")
	private Timestamp matUsedTime;
	
	@Column(name="MON_DEPT_NAME")
	private String monDeptName;
	
	@Column(name="CASE_STATUS_DEFECT")
	private int caseStatusDefect;
	
	@Column(name="DEFECT_MATCH_STATUS")
	private String defectMatchStatus;
	
	@Column(name="IS_NEW_BATCH")
	private String isNewBatch;
	
	@Column(name="CASE_CLOSE_TIME")
	private Timestamp caseCloseTime;
	
	@Column(name="CASE_STATUS_FAC")
	private int caseStatusFac;
	
	@Column(name="FAC_MATCH_STATUS")
	private String facMatchStatus;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getFabName() {
		return fabName;
	}

	public void setFabName(String fabName) {
		this.fabName = fabName;
	}

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public String getMatNo() {
		return matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getContrId() {
		return contrId;
	}

	public void setContrId(String contrId) {
		this.contrId = contrId;
	}

	public Timestamp getMatChangeTime() {
		return matChangeTime;
	}

	public void setMatChangeTime(Timestamp matChangeTime) {
		this.matChangeTime = matChangeTime;
	}

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Timestamp getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Timestamp lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getUpdateSystem() {
		return updateSystem;
	}

	public void setUpdateSystem(String updateSystem) {
		this.updateSystem = updateSystem;
	}

	public String getMailList() {
		return mailList;
	}

	public void setMailList(String mailList) {
		this.mailList = mailList;
	}

	public String getMonitorSection() {
		return monitorSection;
	}

	public void setMonitorSection(String monitorSection) {
		this.monitorSection = monitorSection;
	}

	public String getPreqResult() {
		return preqResult;
	}

	public void setPreqResult(String preqResult) {
		this.preqResult = preqResult;
	}

	public String getSpcMatchStatus() {
		return spcMatchStatus;
	}

	public void setSpcMatchStatus(String spcMatchStatus) {
		this.spcMatchStatus = spcMatchStatus;
	}

	public String getFabPlantCode() {
		return fabPlantCode;
	}

	public void setFabPlantCode(String fabPlantCode) {
		this.fabPlantCode = fabPlantCode;
	}

	public Timestamp getMatUsedTime() {
		return matUsedTime;
	}

	public void setMatUsedTime(Timestamp matUsedTime) {
		this.matUsedTime = matUsedTime;
	}

	public String getMonDeptName() {
		return monDeptName;
	}

	public void setMonDeptName(String monDeptName) {
		this.monDeptName = monDeptName;
	}

	public int getCaseStatusDefect() {
		return caseStatusDefect;
	}

	public void setCaseStatusDefect(int caseStatusDefect) {
		this.caseStatusDefect = caseStatusDefect;
	}

	public String getDefectMatchStatus() {
		return defectMatchStatus;
	}

	public void setDefectMatchStatus(String defectMatchStatus) {
		this.defectMatchStatus = defectMatchStatus;
	}

	public String getIsNewBatch() {
		return isNewBatch;
	}

	public void setIsNewBatch(String isNewBatch) {
		this.isNewBatch = isNewBatch;
	}

	public Timestamp getCaseCloseTime() {
		return caseCloseTime;
	}

	public void setCaseCloseTime(Timestamp caseCloseTime) {
		this.caseCloseTime = caseCloseTime;
	}

	public int getCaseStatusFac() {
		return caseStatusFac;
	}

	public void setCaseStatusFac(int caseStatusFac) {
		this.caseStatusFac = caseStatusFac;
	}

	public String getFacMatchStatus() {
		return facMatchStatus;
	}

	public void setFacMatchStatus(String facMatchStatus) {
		this.facMatchStatus = facMatchStatus;
	}

	

}
