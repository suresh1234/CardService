package com.cardservice.pojo;

/**
 * Error holder object for keeping track of error and returning the same.
 * @author mac_osx
 *
 */
public class ErrorHolder {
	/**
	 * Error message 
	 */
	String erroMessage;
	/**
	 * status true indicate everythig is fine.
	 * False indicate error condition.
	 */
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
