package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException {

	public UnauthorizedException(BaseCode errorCode) {
		super(errorCode);
	}
}
