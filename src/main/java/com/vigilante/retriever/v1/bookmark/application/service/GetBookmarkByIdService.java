package com.vigilante.retriever.v1.bookmark.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.bookmark.application.query.BookmarkMongoQuery;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarkByIdUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBookmarkByIdService implements GetBookmarkByIdUseCase {

	private final BookmarkMongoQuery bookmarkMongoQuery;

	@Override
	public BookmarkEntity getBookmarkById(String bookmarkId) {
		return bookmarkMongoQuery.getById(bookmarkId);
	}
}
