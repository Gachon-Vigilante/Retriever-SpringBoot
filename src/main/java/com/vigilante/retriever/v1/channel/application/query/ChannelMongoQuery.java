package com.vigilante.retriever.v1.channel.application.query;

import static com.vigilante.retriever.v1.channel.domain.code.ChannelErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;
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
		return channelMongoPort.findByChannelId(channelId).orElseThrow(() -> new NotFoundException(CHANNEL_NOT_FOUND));
	}

	public List<ChannelEntity> findByTitleContaining(String title) {
		return channelMongoPort.findByTitleContaining(title);
	}
}
