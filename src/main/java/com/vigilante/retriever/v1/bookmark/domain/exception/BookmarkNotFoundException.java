package com.vigilante.retriever.v1.bookmark.domain.exception;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class BookmarkNotFoundException extends NotFoundException {

	public BookmarkNotFoundException() {
		super(ARGOT_NOT_FOUND);
	}
}
