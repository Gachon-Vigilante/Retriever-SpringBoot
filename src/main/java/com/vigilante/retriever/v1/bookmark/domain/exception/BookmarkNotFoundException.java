package com.vigilante.retriever.v1.bookmark.domain.exception;

import static com.vigilante.retriever.v1.bookmark.domain.code.BookmarkErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class BookmarkNotFoundException extends NotFoundException {

	public BookmarkNotFoundException() {
		super(BOOKMARK_NOT_FOUND);
	}
}
