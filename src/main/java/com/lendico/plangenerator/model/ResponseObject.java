package com.lendico.plangenerator.model;

import java.sql.Timestamp;


public class ResponseObject {

	private Double borrowerPaymentAmount;
	private Timestamp date;
	private Double initialOutstandingPrincipal;
	private Double interest;
	private Double principal;
	private Double remainingOutstandingPrincipals;
	public ResponseObject(Double borrowerPaymentAmount, Timestamp date, Double initialOutstandingPrincipal,
			Double interest, Double principal, Double remainingOutstandingPrincipals) {
		super();
		this.borrowerPaymentAmount = borrowerPaymentAmount;
		this.date = date;
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
		this.interest = interest;
		this.principal = principal;
		this.remainingOutstandingPrincipals = remainingOutstandingPrincipals;
	}
	public ResponseObject() {
		super();
	}
	public Double getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}
	public void setBorrowerPaymentAmount(Double borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Double getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}
	public void setInitialOutstandingPrincipal(Double initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Double getPrincipal() {
		return principal;
	}
	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
	public Double getRemainingOutstandingPrincipals() {
		return remainingOutstandingPrincipals;
	}
	public void setRemainingOutstandingPrincipals(Double remainingOutstandingPrincipals) {
		this.remainingOutstandingPrincipals = remainingOutstandingPrincipals;
	}
	@Override
	public String toString() {
		return "ResponseObject [borrowerPaymentAmount=" + borrowerPaymentAmount + ", date=" + date
				+ ", initialOutstandingPrincipal=" + initialOutstandingPrincipal + ", interest=" + interest
				+ ", principal=" + principal + ", remainingOutstandingPrincipals=" + remainingOutstandingPrincipals
				+ "]";
	}
    
    public ResponseObject build() {
		return new ResponseObject(borrowerPaymentAmount, date, initialOutstandingPrincipal, interest, principal, remainingOutstandingPrincipals);
    	
    }
    
}
