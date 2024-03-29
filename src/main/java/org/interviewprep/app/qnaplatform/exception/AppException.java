package org.interviewprep.app.qnaplatform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String message;

}
