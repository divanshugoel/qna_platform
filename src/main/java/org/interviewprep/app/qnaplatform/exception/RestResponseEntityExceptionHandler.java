package org.interviewprep.app.qnaplatform.exception;

import java.util.Date;

import org.interviewprep.app.qnaplatform.responseDTO.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	protected ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(new Date(), ex.getErrorCode(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(new Date(), HttpStatus.BAD_REQUEST.value(),
				"Validation Failed. " + ex.getLocalizedMessage());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
}
