package com.vigilante.retriever.v1.drug.domain.exception;

import static com.vigilante.retriever.v1.drug.domain.code.ArgotErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ArgotNotFoundException extends NotFoundException {

	public ArgotNotFoundException() {
		super(ARGOT_NOT_FOUND);
	}
}
