package com.vigilante.retriever.v1.bookmark.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;

public interface BookmarkPersistencePort {

	List<BookmarkEntity> findAll();

	List<BookmarkEntity> findByTelegramUserId(String telegramUserId);

	Optional<BookmarkEntity> findById(String id);

	BookmarkEntity save(BookmarkEntity bookmarkEntity);

	void deleteById(String id);
}
