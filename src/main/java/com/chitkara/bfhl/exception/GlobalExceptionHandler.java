package com.chitkara.bfhl.exception;

import com.chitkara.bfhl.config.BfhlUserProperties;
import com.chitkara.bfhl.dto.BfhlResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final BfhlUserProperties userProperties;

	public GlobalExceptionHandler(BfhlUserProperties userProperties) {
		this.userProperties = userProperties;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BfhlResponse> handleException(Exception exception) {
		BfhlResponse response = new BfhlResponse(
				false,
				userProperties.getUserId(),
				userProperties.getEmail(),
				userProperties.getRollNumber(),
				List.of(),
				List.of(),
				List.of(),
				List.of(),
				"0",
				"");
		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<BfhlResponse> handleInvalidRequest(HttpMessageNotReadableException exception) {
		return handleException(exception);
	}
}
