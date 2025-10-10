package com.vigilante.retriever.v1.user.domain.exception;

import static com.vigilante.retriever.v1.user.domain.enums.UserErrorCode.*;

import com.vigilante.retriever.common.domain.exception.ConflictException;

public class UserDuplicatedException extends ConflictException {

	public UserDuplicatedException() {
		super(USER_DUPLICATED);
	}
}
