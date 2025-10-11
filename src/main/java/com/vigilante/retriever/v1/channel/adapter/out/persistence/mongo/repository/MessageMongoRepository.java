package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.MessageDocument;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageDocument, String> {

	// 채널 아이디로 조회
	List<MessageDocument> findByChannelId(long channelId);
}
