package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;
import com.vigilante.retriever.v1.channel.domain.exception.MessageNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelMongoQuery {

	private final ChannelMongoPort channelMongoPort;

	public List<ChannelEntity> findAll() {
		return channelMongoPort.findAll();
	}

	public ChannelEntity getByChannelId(Long channelId) {
		return channelMongoPort.findByChannelId(channelId)
			.orElseThrow(MessageNotFoundException::new);
	}

	public List<ChannelEntity> findByTitleContaining(String title) {
		return channelMongoPort.findByTitleContaining(title);
	}
}
