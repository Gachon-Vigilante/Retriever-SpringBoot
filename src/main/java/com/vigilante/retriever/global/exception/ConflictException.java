package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class ConflictException extends BaseException {

	public ConflictException(BaseCode errorCode) {
		super(errorCode);
	}
}
