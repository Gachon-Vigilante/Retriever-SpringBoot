package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelDataNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelDataMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelDataQuery {

	private final ChannelDataMongoPort channelDataMongoPort;

	public List<ChannelDataEntity> findAll() {
		return channelDataMongoPort.findAll();
	}

	public ChannelDataEntity getById(String id) {
		return channelDataMongoPort.findById(id)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public List<ChannelDataEntity> findByChannelId(long channelId) {
		return channelDataMongoPort.findByChannelId(channelId);
	}
}
