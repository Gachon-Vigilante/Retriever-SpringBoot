package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelDocument;

@Repository
public interface ChannelMongoRepository extends MongoRepository<ChannelDocument, String> {

	Optional<ChannelDocument> findById(long id);

	// TODO: 로직 재설계 필요
	// // 채널 링크로 조회
	// Optional<ChannelDocument> findByLink(String link);

	/* 250102 추가 */
	// 채널 이름에 포함 (대소문자 무시)
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<ChannelDocument> findByTitleContaining(String title);

	boolean existsById(String id);
}
