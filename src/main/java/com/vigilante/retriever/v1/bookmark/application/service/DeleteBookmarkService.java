package com.vigilante.retriever.v1.bookmark.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.common.domain.exception.ForbiddenException;
import com.vigilante.retriever.v1.bookmark.application.command.BookmarkMongoCommand;
import com.vigilante.retriever.v1.bookmark.application.query.BookmarkMongoQuery;
import com.vigilante.retriever.v1.bookmark.domain.code.BookmarkErrorCode;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.in.DeleteBookmarkUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteBookmarkService implements DeleteBookmarkUseCase {

	private final BookmarkMongoCommand bookmarkMongoCommand;
	private final BookmarkMongoQuery bookmarkMongoQuery;

	@Override
	public void deleteBookmark(String userId, String bookmarkId) {
		BookmarkEntity bookmark = bookmarkMongoQuery.getById(bookmarkId);
		if (!bookmark.userId().equals(userId)) {
			throw new ForbiddenException(BookmarkErrorCode.CANNOT_DELETE_OTHERS_BOOKMARK);
		}
		bookmarkMongoCommand.deleteById(bookmarkId);
	}
}
