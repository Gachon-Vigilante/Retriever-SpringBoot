package com.vigilante.retriever.common.domain.exception;

import static com.vigilante.retriever.common.domain.code.RoleErrorCode.*;

public class RoleNotFoundException extends NotFoundException {

	public RoleNotFoundException() {
		super(ROLE_NOT_FOUND);
	}
}
