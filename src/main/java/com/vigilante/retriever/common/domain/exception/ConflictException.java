package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class ConflictException extends BaseException {

	public ConflictException(BaseCode errorCode) {
		super(errorCode);
	}
}
