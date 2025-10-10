package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final BaseCode errorCode;

	public BaseException(BaseCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
