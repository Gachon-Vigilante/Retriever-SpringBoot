package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final BaseCode errorCode;

	public BaseException(BaseCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
