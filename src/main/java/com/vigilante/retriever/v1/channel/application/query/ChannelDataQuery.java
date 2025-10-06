package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelDataNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelDataPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelDataQuery {

	private final ChannelDataPersistencePort channelDataPersistencePort;

	public List<ChannelDataEntity> findAll() {
		return channelDataPersistencePort.findAll();
	}

	public ChannelDataEntity getById(String id) {
		return channelDataPersistencePort.findById(id)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public List<ChannelDataEntity> findByChannelId(long channelId) {
		return channelDataPersistencePort.findByChannelId(channelId);
	}
}
