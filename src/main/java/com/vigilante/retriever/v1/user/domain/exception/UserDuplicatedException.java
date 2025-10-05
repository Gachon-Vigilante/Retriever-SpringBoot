package com.vigilante.retriever.v1.user.domain.exception;

import static com.vigilante.retriever.v1.user.domain.enums.UserErrorCode.*;

import com.vigilante.retriever.global.exception.NotFoundException;

public class UserDuplicatedException extends NotFoundException {

	public UserDuplicatedException() {
		super(USER_DUPLICATED);
	}
}
