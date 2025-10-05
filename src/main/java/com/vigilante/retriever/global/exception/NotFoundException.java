package com.vigilante.retriever.global.exception;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

	public NotFoundException(BaseCode errorCode) {
		super(errorCode);
	}
}
