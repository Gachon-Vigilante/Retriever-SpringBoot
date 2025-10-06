package com.vigilante.retriever.v1.bookmark.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkPersistencePort;
import com.vigilante.retriever.v1.bookmark.infrastructure.mapper.BookmarkPersistenceMapper;
import com.vigilante.retriever.v1.bookmark.infrastructure.persistence.document.BookmarkDocument;
import com.vigilante.retriever.v1.bookmark.infrastructure.persistence.repository.BookmarkMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryAdapter implements BookmarkPersistencePort {

	private final BookmarkMongoRepository bookmarkMongoRepository;
	private final BookmarkPersistenceMapper bookmarkPersistenceMapper;

	@Override
	public List<BookmarkEntity> findAll() {
		List<BookmarkDocument> allBookmark = bookmarkMongoRepository.findAll();
		return bookmarkPersistenceMapper.getEntityList(allBookmark);
	}

	@Override
	public List<BookmarkEntity> findByTelegramUserId(String telegramUserId) {
		List<BookmarkDocument> bookmarkList = bookmarkMongoRepository.findByTelegramUserId(telegramUserId);
		return bookmarkPersistenceMapper.getEntityList(bookmarkList);
	}

	@Override
	public Optional<BookmarkEntity> findById(String id) {
		return bookmarkMongoRepository.findById(id).map(bookmarkPersistenceMapper::toEntity);
	}

	@Override
	public BookmarkEntity save(BookmarkEntity bookmarkEntity) {
		BookmarkDocument bookmarkDocument = bookmarkPersistenceMapper.toDocument(bookmarkEntity);
		BookmarkDocument savedDocument = bookmarkMongoRepository.save(bookmarkDocument);
		return bookmarkPersistenceMapper.toEntity(savedDocument);
	}

	@Override
	public void deleteById(String id) {
		bookmarkMongoRepository.deleteById(id);
	}
}
