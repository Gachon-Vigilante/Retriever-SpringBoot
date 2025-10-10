package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

	public BadRequestException(BaseCode errorCode) {
		super(errorCode);
	}
}
