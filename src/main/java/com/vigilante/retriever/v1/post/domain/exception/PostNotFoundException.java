package com.vigilante.retriever.v1.post.domain.exception;

import static com.vigilante.retriever.v1.post.domain.enums.PostErrorCode.*;

import com.vigilante.retriever.global.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {

	public PostNotFoundException() {
		super(POST_NOT_FOUND);
	}
}
