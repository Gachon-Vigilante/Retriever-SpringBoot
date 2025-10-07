package com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.document.ChannelInfoDocument;

@Repository
public interface ChannelInfoMongoRepository extends MongoRepository<ChannelInfoDocument, String> {

	Optional<ChannelInfoDocument> findById(long id);

	// 채널 링크로 조회
	Optional<ChannelInfoDocument> findByLink(String link);

	/* 250102 추가 */
	// 채널 이름에 포함 (대소문자 무시)
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<ChannelInfoDocument> findByTitleContaining(String title);

	boolean existsById(String id);
}
