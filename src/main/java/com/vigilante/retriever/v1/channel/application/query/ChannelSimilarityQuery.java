package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelSimilarityNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelSimilarityPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelSimilarityQuery {

	private final ChannelSimilarityPersistencePort channelSimilarityPersistencePort;

	public List<ChannelSimilarityEntity> findAll() {
		return channelSimilarityPersistencePort.findAll();
	}

	public ChannelSimilarityEntity getById(String id) {
		return channelSimilarityPersistencePort.findById(id)
			.orElseThrow(ChannelSimilarityNotFoundException::new);
	}

	public ChannelSimilarityEntity getByChannelId(long channelId) {
		return channelSimilarityPersistencePort.findByChannelId(channelId)
			.orElseThrow(ChannelSimilarityNotFoundException::new);
	}
}
