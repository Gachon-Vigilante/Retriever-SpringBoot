package com.vigilante.retriever.adapter.web.exception.handler;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.vigilante.retriever.adapter.web.code.CommonErrorCode;
import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.common.domain.code.BaseCode;
import com.vigilante.retriever.common.domain.exception.BadRequestException;
import com.vigilante.retriever.common.domain.exception.ConflictException;
import com.vigilante.retriever.common.domain.exception.ForbiddenException;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.common.domain.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CommonResponse<Void>> handleNotFound(NotFoundException ex) {
		return build(ex.getErrorCode(), NOT_FOUND, ex);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<CommonResponse<Void>> handleConflict(ConflictException ex) {
		return build(ex.getErrorCode(), CONFLICT, ex);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<CommonResponse<Void>> handleBadRequest(BadRequestException ex) {
		return build(ex.getErrorCode(), BAD_REQUEST, ex);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<CommonResponse<Void>> handleUnauthorized(UnauthorizedException ex) {
		return build(ex.getErrorCode(), UNAUTHORIZED, ex);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<CommonResponse<Void>> handleForbidden(ForbiddenException ex) {
		return build(ex.getErrorCode(), FORBIDDEN, ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CommonResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
		String aggregated = ex.getBindingResult().getFieldErrors().stream()
			.map(err -> err.getField() + ": " + err.getDefaultMessage())
			.distinct()
			.reduce((a, b) -> a + ", " + b)
			.orElse("Validation error");
		return build(CommonErrorCode.VALIDATION_ERROR, aggregated, BAD_REQUEST, ex);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<CommonResponse<Void>> handleMissingParam(MissingServletRequestParameterException ex) {
		String message = "Missing required parameter: " + ex.getParameterName();
		return build(CommonErrorCode.BAD_REQUEST, message, BAD_REQUEST, ex);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<CommonResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown";
		String message = "Invalid value for parameter: " + ex.getName() + " (Expected: " + requiredType + ")";
		return build(CommonErrorCode.BAD_REQUEST, message, BAD_REQUEST, ex);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse<Void>> handleUnexpected(Exception ex) {
		log.error("Unexpected error", ex);
		return build(CommonErrorCode.INTERNAL_SERVER_ERROR, null, INTERNAL_SERVER_ERROR, ex);
	}

	private ResponseEntity<CommonResponse<Void>> build(BaseCode errorCode, HttpStatus status,
		Exception ex) {
		logException(status, errorCode.getCode(), errorCode.getMessage(), ex);
		CommonResponse<Void> body = CommonResponse.error(errorCode);
		return ResponseEntity.status(status).body(body);
	}

	private ResponseEntity<CommonResponse<Void>> build(CommonErrorCode errorCode,
		String overrideMessage, HttpStatus status, Exception ex) {
		String message = overrideMessage != null ? overrideMessage : errorCode.getMessage();
		logException(status, errorCode.getCode(), message, ex);
		CommonResponse<Void> body = overrideMessage != null
			? CommonResponse.error(errorCode, overrideMessage)
			: CommonResponse.error(errorCode);
		return ResponseEntity.status(status).body(body);
	}

	private void logException(HttpStatus status, String code, String message, Exception ex) {
		String logMsg = String.format("[%d] code=%s msg=%s", status.value(), code, message);

		if (status.is4xxClientError()) {
			log.warn(logMsg);
			return;
		}

		log.error(logMsg, ex);
	}
}
