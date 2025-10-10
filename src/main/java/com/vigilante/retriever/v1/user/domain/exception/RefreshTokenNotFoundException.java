package com.vigilante.retriever.v1.user.domain.exception;

import static com.vigilante.retriever.v1.user.domain.enums.RefreshTokenErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class RefreshTokenNotFoundException extends NotFoundException {

	public RefreshTokenNotFoundException() {
		super(REFRESH_TOKEN_NOT_FOUND);
	}
}
