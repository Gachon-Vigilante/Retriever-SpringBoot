package com.vigilante.retriever.v1.bookmark.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.bookmark.application.query.BookmarkMongoQuery;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarksByUserUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBookmarksByUserService implements GetBookmarksByUserUseCase {

	private final BookmarkMongoQuery bookmarkMongoQuery;

	@Override
	public List<BookmarkEntity> getBookmarksByUserId(String userId) {
		return bookmarkMongoQuery.findByUserId(userId);
	}
}
