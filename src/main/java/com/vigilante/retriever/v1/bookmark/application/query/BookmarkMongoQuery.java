package com.vigilante.retriever.v1.bookmark.application.query;

import static com.vigilante.retriever.v1.bookmark.domain.code.BookmarkErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class BookmarkMongoQuery {

	private final BookmarkMongoPort bookmarkMongoPort;

	public List<BookmarkEntity> findAll() {
		return bookmarkMongoPort.findAll();
	}

	public List<BookmarkEntity> findByUserId(String userId) {
		return bookmarkMongoPort.findByUserId(userId);
	}

	public BookmarkEntity getById(String id) {
		return bookmarkMongoPort.findById(id).orElseThrow(() -> new NotFoundException(BOOKMARK_NOT_FOUND));
	}

	public boolean existsByUserIdAndChannelId(String userId, String channelId) {
		return bookmarkMongoPort.existsByUserIdAndChannelId(userId, channelId);
	}
}
