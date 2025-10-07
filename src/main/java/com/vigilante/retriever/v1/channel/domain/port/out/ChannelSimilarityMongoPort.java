package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;

public interface ChannelSimilarityMongoPort {

	List<ChannelSimilarityEntity> findAll();

	Optional<ChannelSimilarityEntity> findById(String id);

	Optional<ChannelSimilarityEntity> findByChannelId(long channelId);
}
