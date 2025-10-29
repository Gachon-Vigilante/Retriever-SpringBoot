package com.vigilante.retriever.v1.bookmark.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.bookmark.application.query.BookmarkMongoQuery;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarkUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBookmarkService implements GetBookmarkUseCase {

	private final BookmarkMongoQuery bookmarkMongoQuery;

	@Override
	public BookmarkEntity getById(String bookmarkId) {
		return bookmarkMongoQuery.getById(bookmarkId);
	}

	@Override
	public List<BookmarkEntity> findByUserId(String userId) {
		return bookmarkMongoQuery.findByUserId(userId);
	}
}
