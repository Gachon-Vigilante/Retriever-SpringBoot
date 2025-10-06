package com.vigilante.retriever.v1.channel.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.infrastructure.persistence.document.ChannelSimilarityDocument;

@Repository
public interface ChannelSimilarityMongoRepository extends MongoRepository<ChannelSimilarityDocument, String> {

	Optional<ChannelSimilarityDocument> findByChannelId(long channelId);
}
