package com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.document.BookmarkDocument;

@Repository
public interface BookmarkMongoRepository extends MongoRepository<BookmarkDocument, String> {

	// 텔레그램 사용자 아이디로 조회
	List<BookmarkDocument> findByUserId(String userId);

	boolean existsByUserIdAndChannelId(String userId, String channelId);
}
