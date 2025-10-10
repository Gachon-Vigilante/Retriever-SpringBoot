package com.vigilante.retriever.v1.chatbot.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.chatbot.adapter.out.persistence.mongo.document.ChatBotDocument;

@Repository
public interface ChatBotMongoRepository extends MongoRepository<ChatBotDocument, String> {

	List<ChatBotDocument> findByChannels(List<Long> channelIds);
}
