package org.interviewprep.app.qnaplatform.responseDTO;

public enum ErrorMessages {

	ALREADY_EXISTS(101, "already exists"), 
	DOES_NOT_EXIST(102,"doesnot exist");

	private Integer errorCode;
	private String errorMessages;

	private ErrorMessages(Integer errorCode, String errorMessages) {
		this.errorMessages = errorMessages;
		this.errorCode = errorCode;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
