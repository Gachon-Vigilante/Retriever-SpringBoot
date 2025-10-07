package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

	public BadRequestException(BaseCode errorCode) {
		super(errorCode);
	}
}
