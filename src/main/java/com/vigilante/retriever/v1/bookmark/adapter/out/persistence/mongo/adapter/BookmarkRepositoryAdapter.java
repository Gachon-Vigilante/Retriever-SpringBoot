package com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.bookmark.adapter.out.mapper.BookmarkMongoMapper;
import com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.document.BookmarkDocument;
import com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.repository.BookmarkMongoRepository;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.domain.port.out.BookmarkMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkRepositoryAdapter implements BookmarkMongoPort {

	private final BookmarkMongoRepository bookmarkMongoRepository;
	private final BookmarkMongoMapper bookmarkPersistenceMapper;

	@Override
	public List<BookmarkEntity> findAll() {
		List<BookmarkDocument> allBookmark = bookmarkMongoRepository.findAll();
		return bookmarkPersistenceMapper.getEntityList(allBookmark);
	}

	@Override
	public List<BookmarkEntity> findByUserId(String userId) {
		List<BookmarkDocument> bookmarkList = bookmarkMongoRepository.findByUserId(userId);
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

	@Override
	public boolean existsByUserIdAndChannelId(String userId, String channelId) {
		return bookmarkMongoRepository.existsByUserIdAndChannelId(userId, channelId);
	}
}
