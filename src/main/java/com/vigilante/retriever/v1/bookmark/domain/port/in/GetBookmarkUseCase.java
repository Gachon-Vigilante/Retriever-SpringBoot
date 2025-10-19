package com.vigilante.retriever.v1.bookmark.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;

public interface GetBookmarkUseCase {

	List<BookmarkEntity> findAll();

	BookmarkEntity getById(String bookmarkId);

	List<BookmarkEntity> findByUserId(String userId);
}
