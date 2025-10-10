package com.vigilante.retriever.v1.post.domain.exception;

import static com.vigilante.retriever.v1.post.domain.code.PostErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {

	public PostNotFoundException() {
		super(POST_NOT_FOUND);
	}
}
