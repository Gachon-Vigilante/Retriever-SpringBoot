package com.vigilante.retriever.v1.bookmark.domain.port.in;

import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;

public interface GetBookmarkByIdUseCase {
	BookmarkEntity getBookmarkById(String bookmarkId);
}
