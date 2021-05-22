package com.lendico.plangenerator.model;

import java.sql.Timestamp;


public class RequestObject {

	private Double loanAmount;
	private Float nominalRate;
	private Integer duration;
	private Timestamp  startDate;
	public RequestObject(Double loanAmount, Float nominalRate, Integer duration, Timestamp startDate) {
		super();
		this.loanAmount = loanAmount;
		this.nominalRate = nominalRate;
		this.duration = duration;
		this.startDate = startDate;
	}
	public RequestObject() {
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Float getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(Float nominalRate) {
		this.nominalRate = nominalRate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "RequestObject [loanAmount=" + loanAmount + ", nominalRate=" + nominalRate + ", duration=" + duration
				+ ", startDate=" + startDate + "]";
	}
    
    
}
