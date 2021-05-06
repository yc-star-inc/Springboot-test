package com.tsmc.prequal.data.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultRawVo extends MonitorCriteriaVo{

	private List<TargetPointVo> targetList;

	
	@JsonProperty("target")
	public List<TargetPointVo> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TargetPointVo> targetList) {
		this.targetList = targetList;
	} 
	
	
	
}
