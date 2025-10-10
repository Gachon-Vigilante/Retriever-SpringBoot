package com.vigilante.retriever.common.domain.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

	public NotFoundException(BaseCode errorCode) {
		super(errorCode);
	}
}
