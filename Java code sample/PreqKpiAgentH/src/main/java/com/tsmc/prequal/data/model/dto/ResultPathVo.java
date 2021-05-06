package com.tsmc.prequal.data.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPathVo {
	
	public static String writeAsJson(ResultPathVo _curObj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.setDateFormat(DateUtils.DateTimeFormat);
		return objectMapper.writeValueAsString(_curObj);
	}

	public static ResultPathVo readFromJson(String _jsonString) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();		
		ResultPathVo rtnObj = objectMapper.readValue(_jsonString, ResultPathVo.class);
		return rtnObj;
	}
	
	String imgSaoId;
	String imgUrl;
	String rawDataSaoId;
	String rawDataStartDate;
	String rawDataEndDate;
	String targetStartDate;
	String targetEndDate;
	Map<String, String> criteria;

	public ResultPathVo(String imgSaoId, String imgUrl, String rawDataSaoId, String rawDataStartDate,
			String rawDataEndDate, String targetStartDate, String targetEndDate, Map<String, String> criteria) {
		super();
		this.imgSaoId = imgSaoId;
		this.imgUrl = imgUrl;
		this.rawDataSaoId = rawDataSaoId;
		this.rawDataStartDate = rawDataStartDate;
		this.rawDataEndDate = rawDataEndDate;
		this.targetStartDate = targetStartDate;
		this.targetEndDate = targetEndDate;
		this.criteria = criteria;
	}

	public ResultPathVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResultPathVo [imgSaoId=" + imgSaoId + ", imgUrl=" + imgUrl + ", rawDataSaoId=" + rawDataSaoId
				+ ", rawDataStartDate=" + rawDataStartDate + ", rawDataEndDate=" + rawDataEndDate + ", targetStartDate="
				+ targetStartDate + ", targetEndDate=" + targetEndDate + ", criteria=" + criteria + "]";
	}

	public String getImgSaoId() {
		return imgSaoId;
	}

	public void setImgSaoId(String imgSaoId) {
		this.imgSaoId = imgSaoId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRawDataSaoId() {
		return rawDataSaoId;
	}

	public void setRawDataSaoId(String rawDataSaoId) {
		this.rawDataSaoId = rawDataSaoId;
	}

	public String getRawDataStartDate() {
		return rawDataStartDate;
	}

	public void setRawDataStartDate(String rawDataStartDate) {
		this.rawDataStartDate = rawDataStartDate;
	}

	public String getRawDataEndDate() {
		return rawDataEndDate;
	}

	public void setRawDataEndDate(String rawDataEndDate) {
		this.rawDataEndDate = rawDataEndDate;
	}

	public String getTargetStartDate() {
		return targetStartDate;
	}

	public void setTargetStartDate(String targetStartDate) {
		this.targetStartDate = targetStartDate;
	}

	public String getTargetEndDate() {
		return targetEndDate;
	}

	public void setTargetEndDate(String targetEndDate) {
		this.targetEndDate = targetEndDate;
	}

	public Map<String, String> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, String> criteria) {
		this.criteria = criteria;
	}

}
