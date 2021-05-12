package com.tsmc.prequal.data.model.dto;

import java.lang.reflect.Field;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonitorCriteriaVo {

	public static String writeAsJson(MonitorCriteriaVo _curObj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.setDateFormat(DateUtils.DateTimeFormat);
		return objectMapper.writeValueAsString(_curObj);
	}

	public static MonitorCriteriaVo readFromJson(String _jsonString) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();		
		MonitorCriteriaVo rtnObj = objectMapper.readValue(_jsonString, MonitorCriteriaVo.class);
		return rtnObj;
	}

	private String fabName;
	private String matPhaseId;
	private String plantCode;
	private String matNo;
	private String batchId;
	private String contrId;
	
	//@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private String matChangeTime;
	private String matUsedTime;
	private String ntAccount;
	private String chartTitle;

	private Map<String, String> criteria; // ${chartId}

	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}
	
	
	
	public String getRuleCheckRslt(String _key) {

		if (this.criteria != null && this.criteria.containsKey(_key)) {
			return this.criteria.get(_key);
		} else {
			return "";
		}
	}

	public void setRuleCheckRslt(String _key, String _value) {

		if (this.criteria != null && this.criteria.containsKey(_key)) {
			this.criteria.replace(_key, this.criteria.get(_key), _value);
		} else {
			this.criteria.put(_key, _value);
		}
	}

	public String getFabName() {
		return fabName;
	}

	public void setFabName(String fabName) {
		this.fabName = fabName;
	}

	public String getMatPhaseId() {
		return matPhaseId;
	}

	public void setMatPhaseId(String matPhaseId) {
		this.matPhaseId = matPhaseId;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
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

	public String getMatChangeTime() {
		return matChangeTime;
	}

	public void setMatChangeTime(String matChangeTime) {
		this.matChangeTime = matChangeTime;
	}

	public String getMatUsedTime() {
		return matUsedTime;
	}

	public void setMatUsedTime(String matUsedTime) {
		this.matUsedTime = matUsedTime;
	}

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public Map<String, String> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, String> criteria) {
		this.criteria = criteria;
	}

}
