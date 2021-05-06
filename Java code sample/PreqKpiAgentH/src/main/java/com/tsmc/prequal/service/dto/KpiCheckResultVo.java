package com.tsmc.prequal.service.dto;

public class KpiCheckResultVo {

	private String oldStatus = "";
	private String newStatus = "";
	private String memo = "";

	public KpiCheckResultVo(String _oldStatus) {
		this.newStatus = this.oldStatus = _oldStatus; 
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
