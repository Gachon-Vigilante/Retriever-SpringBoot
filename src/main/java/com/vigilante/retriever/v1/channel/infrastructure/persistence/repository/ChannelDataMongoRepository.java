package com.vigilante.retriever.v1.channel.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.infrastructure.persistence.document.ChannelDataDocument;

@Repository
public interface ChannelDataMongoRepository extends MongoRepository<ChannelDataDocument, String> {

	// 채널 아이디로 조회
	List<ChannelDataDocument> findByChannelId(long channelId);
}
