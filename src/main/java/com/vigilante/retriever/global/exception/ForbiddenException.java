package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class ForbiddenException extends BaseException {

	public ForbiddenException(BaseCode errorCode) {
		super(errorCode);
	}
}
