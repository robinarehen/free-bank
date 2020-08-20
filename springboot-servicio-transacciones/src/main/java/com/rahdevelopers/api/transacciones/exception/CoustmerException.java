package com.rahdevelopers.api.transacciones.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoustmerException {

	private Map<String, String> responseException;

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> illegalException(IllegalArgumentException exception) {
		responseException = new HashMap<>();

		responseException.put("status", "error");
		responseException.put("statusCode", "400");
		responseException.put("message", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
	}
}
