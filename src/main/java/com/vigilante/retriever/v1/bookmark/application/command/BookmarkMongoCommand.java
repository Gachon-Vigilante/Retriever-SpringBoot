package com.vigilante.retriever.v1.bookmark.application.command;

import com.vigilante.retriever.common.domain.annotation.CommandService;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkMongoPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class BookmarkMongoCommand {

	private final BookmarkMongoPort bookmarkMongoPort;

	public BookmarkEntity save(BookmarkEntity bookmarkEntity) {
		return bookmarkMongoPort.save(bookmarkEntity);
	}

	public void deleteById(String id) {
		bookmarkMongoPort.deleteById(id);
	}
}
