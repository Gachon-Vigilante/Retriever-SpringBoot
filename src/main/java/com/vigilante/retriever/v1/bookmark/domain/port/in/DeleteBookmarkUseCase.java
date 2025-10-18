package com.vigilante.retriever.v1.bookmark.domain.port.in;

public interface DeleteBookmarkUseCase {
	void deleteBookmark(String userId, String bookmarkId);
}
