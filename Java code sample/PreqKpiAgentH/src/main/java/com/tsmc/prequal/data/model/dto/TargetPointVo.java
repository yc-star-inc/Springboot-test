package com.tsmc.prequal.data.model.dto;

import java.util.Map;

public class TargetPointVo {
	private String waferId;
	private String lotId;
	private String pointValue;
	private String measDate;
	private String ucl;
	private String lcl;
	private String usl;
	private String lsl;
	private String toolId;
	private Map<String, String> ruleChkRslt; // ${ruleChkRslt-1}

	public String getRuleCheckRslt(String _key) {

		if (this.ruleChkRslt != null && this.ruleChkRslt.containsKey(_key)) {
			return this.ruleChkRslt.get(_key);
		} else {
			return "";
		}
	}

	public void setRuleCheckRslt(String _key, String _value) {

		if (this.ruleChkRslt != null && this.ruleChkRslt.containsKey(_key)) {
			this.ruleChkRslt.replace(_key, this.ruleChkRslt.get(_key), _value);
		} else {
			this.ruleChkRslt.put(_key, _value);
		}
	}

	public TargetPointVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TargetPointVo(String waferId, String lotId, String pointValue, String measDate, String ucl, String lcl,
			String usl, String lsl, String toolId, Map<String, String> ruleChkRslt) {
		super();
		this.waferId = waferId;
		this.lotId = lotId;
		this.pointValue = pointValue;
		this.measDate = measDate;
		this.ucl = ucl;
		this.lcl = lcl;
		this.usl = usl;
		this.lsl = lsl;
		this.toolId = toolId;
		this.ruleChkRslt = ruleChkRslt;
	}

	public String getWaferId() {
		return waferId;
	}

	public void setWaferId(String waferId) {
		this.waferId = waferId;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getPointValue() {
		return pointValue;
	}

	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}

	public String getMeasDate() {
		return measDate;
	}

	public void setMeasDate(String measDate) {
		this.measDate = measDate;
	}

	public String getUcl() {
		return ucl;
	}

	public void setUcl(String ucl) {
		this.ucl = ucl;
	}

	public String getLcl() {
		return lcl;
	}

	public void setLcl(String lcl) {
		this.lcl = lcl;
	}

	public String getUsl() {
		return usl;
	}

	public void setUsl(String usl) {
		this.usl = usl;
	}

	public String getLsl() {
		return lsl;
	}

	public void setLsl(String lsl) {
		this.lsl = lsl;
	}

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public Map<String, String> getRuleChkRslt() {
		return ruleChkRslt;
	}

	public void setRuleChkRslt(Map<String, String> ruleChkRslt) {
		this.ruleChkRslt = ruleChkRslt;
	}

}
