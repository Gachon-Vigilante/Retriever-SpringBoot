package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelSimilarityDocument;

@Repository
public interface ChannelSimilarityMongoRepository extends MongoRepository<ChannelSimilarityDocument, String> {

	Optional<ChannelSimilarityDocument> findByChannelId(long channelId);
}
