package com.vigilante.retriever.global.exception.handler;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.vigilante.retriever.global.common.code.BaseCode;
import com.vigilante.retriever.global.common.code.CommonErrorCode;
import com.vigilante.retriever.global.exception.BadRequestException;
import com.vigilante.retriever.global.exception.ConflictException;
import com.vigilante.retriever.global.exception.ForbiddenException;
import com.vigilante.retriever.global.exception.NotFoundException;
import com.vigilante.retriever.global.exception.UnauthorizedException;
import com.vigilante.retriever.global.exception.dto.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
		return build(ex.getErrorCode(), NOT_FOUND, null, ex);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
		return build(ex.getErrorCode(), CONFLICT, null, ex);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
		return build(ex.getErrorCode(), BAD_REQUEST, null, ex);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
		return build(ex.getErrorCode(), UNAUTHORIZED, null, ex);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
		return build(ex.getErrorCode(), FORBIDDEN, null, ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		String aggregated = ex.getBindingResult().getFieldErrors().stream()
			.map(err -> err.getField() + ": " + err.getDefaultMessage())
			.distinct()
			.reduce((a, b) -> a + ", " + b)
			.orElse("Validation error");
		return build(CommonErrorCode.VALIDATION_ERROR, BAD_REQUEST, aggregated, ex);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingParam(MissingServletRequestParameterException ex) {
		String message = "Missing required parameter: " + ex.getParameterName();
		return build(CommonErrorCode.BAD_REQUEST, BAD_REQUEST, message, ex);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown";
		String message = "Invalid value for parameter: " + ex.getName() + " (Expected: " + requiredType + ")";
		return build(CommonErrorCode.BAD_REQUEST, BAD_REQUEST, message, ex);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
		log.error("Unexpected error", ex);
		BaseCode code = CommonErrorCode.INTERNAL_SERVER_ERROR;
		HttpStatus status = HttpStatus.valueOf(CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpCode());
		return build(code, status, null, ex);
	}

	private ResponseEntity<ErrorResponse> build(BaseCode code, HttpStatus status, String overrideMessage,
		Exception ex) {
		logException(status, code, overrideMessage, ex);
		ErrorResponse body = overrideMessage == null ? ErrorResponse.of(code) : ErrorResponse.of(code, overrideMessage);
		return ResponseEntity.status(status).body(body);
	}

	private void logException(HttpStatus status, BaseCode code, String overrideMessage, Exception ex) {
		String logMsg = String.format("[%d] code=%s msg=%s", status.value(), code.getCode(),
			overrideMessage != null ? overrideMessage : code.getMessage());

		if (status.is4xxClientError()) {
			log.warn(logMsg);
			return;
		}

		log.error(logMsg, ex);
	}
}
