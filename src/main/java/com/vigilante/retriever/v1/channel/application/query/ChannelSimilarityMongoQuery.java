package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelSimilarityNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelSimilarityMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelSimilarityMongoQuery {

	private final ChannelSimilarityMongoPort channelSimilarityMongoPort;

	public List<ChannelSimilarityEntity> findAll() {
		return channelSimilarityMongoPort.findAll();
	}

	public ChannelSimilarityEntity getById(String id) {
		return channelSimilarityMongoPort.findById(id)
			.orElseThrow(ChannelSimilarityNotFoundException::new);
	}

	public ChannelSimilarityEntity getByChannelId(long channelId) {
		return channelSimilarityMongoPort.findByChannelId(channelId)
			.orElseThrow(ChannelSimilarityNotFoundException::new);
	}
}
