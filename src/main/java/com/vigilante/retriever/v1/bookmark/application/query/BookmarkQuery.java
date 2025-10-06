package com.vigilante.retriever.v1.bookmark.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.exception.BookmarkNotFoundException;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class BookmarkQuery {

	private final BookmarkPersistencePort bookmarkPersistencePort;

	List<BookmarkEntity> findAll() {
		return bookmarkPersistencePort.findAll();
	}

	List<BookmarkEntity> findByTelegramUserId(String telegramUserId) {
		return bookmarkPersistencePort.findByTelegramUserId(telegramUserId);
	}

	BookmarkEntity getById(String id) {
		return bookmarkPersistencePort.findById(id).orElseThrow(BookmarkNotFoundException::new);
	}
}
