package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class ForbiddenException extends BaseException {

	public ForbiddenException(BaseCode errorCode) {
		super(errorCode);
	}
}
