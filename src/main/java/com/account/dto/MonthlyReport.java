package com.account.dto;

public class MonthlyReport {

	String month;
	String type;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MonthlyReport [month=" + month + ", type=" + type + "]";
	}

}
