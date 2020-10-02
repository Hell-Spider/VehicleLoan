package com.lti.model;

import java.time.LocalDate;

public class EmiClass {
	
	private LocalDate date;
	private String beginningBalance;
	private String EMI;
	private String principal;
	private String interest;
	private String endingBalance;
	public String getBeginningBalance() {
		return beginningBalance;
	}
	public void setBeginningBalance(String beginningBalance) {
		this.beginningBalance = beginningBalance;
	}
	public String getEMI() {
		return EMI;
	}
	public void setEMI(String eMI) {
		EMI = eMI;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(String endingBalance) {
		this.endingBalance = endingBalance;
	}

	private String status;
	
	public EmiClass()
	{
		
	}
	public EmiClass(LocalDate date, String beginningBalance, String eMI, String principal, String interest,
			String endingBalance,String status) {
		super();
		this.date = date;
		this.beginningBalance = beginningBalance;
		this.EMI = eMI;
		this.principal = principal;
		this.interest = interest;
		this.endingBalance = endingBalance;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "EmiClass [date=" + date + ", beginningBalance=" + beginningBalance + ", EMI=" + EMI + ", principal="
				+ principal + ", interest=" + interest + ", endingBalance=" + endingBalance + ", status=" + status
				+ "]";
	}
	
	

}
