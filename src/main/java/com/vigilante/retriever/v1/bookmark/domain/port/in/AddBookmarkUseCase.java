package com.vigilante.retriever.v1.bookmark.domain.port.in;

public interface AddBookmarkUseCase {
	void addBookmark(String userId, String channelId);
}
