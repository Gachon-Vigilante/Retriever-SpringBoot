package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException {

	public UnauthorizedException(BaseCode errorCode) {
		super(errorCode);
	}
}
