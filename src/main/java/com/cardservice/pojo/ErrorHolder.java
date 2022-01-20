package com.cardservice.pojo;

public class ErrorHolder {

	String erroMessage;
	Boolean status;

	public ErrorHolder() {
		this.status = true;
	}

	public Boolean getStatus() {
		return status;
	}

	public ErrorHolder setStatus(Boolean status) {
		this.status = status;
		return this;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public ErrorHolder setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
		return this;
	}

}
