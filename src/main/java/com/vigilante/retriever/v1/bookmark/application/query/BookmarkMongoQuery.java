package com.vigilante.retriever.v1.bookmark.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.exception.BookmarkNotFoundException;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class BookmarkMongoQuery {

	private final BookmarkMongoPort bookmarkMongoPort;

	List<BookmarkEntity> findAll() {
		return bookmarkMongoPort.findAll();
	}

	List<BookmarkEntity> findByTelegramUserId(String telegramUserId) {
		return bookmarkMongoPort.findByTelegramUserId(telegramUserId);
	}

	BookmarkEntity getById(String id) {
		return bookmarkMongoPort.findById(id).orElseThrow(BookmarkNotFoundException::new);
	}
}
