package com.vigilante.retriever.v1.bookmark.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.common.domain.exception.ConflictException;
import com.vigilante.retriever.v1.bookmark.application.command.BookmarkMongoCommand;
import com.vigilante.retriever.v1.bookmark.application.query.BookmarkMongoQuery;
import com.vigilante.retriever.v1.bookmark.domain.code.BookmarkErrorCode;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.in.AddBookmarkUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddBookmarkService implements AddBookmarkUseCase {

	private final BookmarkMongoCommand bookmarkMongoCommand;
	private final BookmarkMongoQuery bookmarkMongoQuery;

	@Override
	public void addBookmark(String userId, String channelId) {
		if (bookmarkMongoQuery.existsByUserIdAndChannelId(userId, channelId)) {
			throw new ConflictException(BookmarkErrorCode.BOOKMARK_ALREADY_EXISTS);
		}
		BookmarkEntity newBookmark = BookmarkEntity.create(channelId, userId);
		bookmarkMongoCommand.save(newBookmark);
	}
}
