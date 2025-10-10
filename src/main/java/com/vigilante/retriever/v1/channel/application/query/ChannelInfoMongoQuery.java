package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelInfoEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelDataNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelInfoMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelInfoMongoQuery {

	private final ChannelInfoMongoPort channelInfoMongoPort;

	public List<ChannelInfoEntity> findAll() {
		return channelInfoMongoPort.findAll();
	}

	public ChannelInfoEntity getById(String id) {
		return channelInfoMongoPort.findById(id)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public ChannelInfoEntity getByLink(String link) {
		return channelInfoMongoPort.findByLink(link)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public List<ChannelInfoEntity> findByTitleContaining(String title) {
		return channelInfoMongoPort.findByTitleContaining(title);
	}

	public boolean existsById(String id) {
		return channelInfoMongoPort.existsById(id);
	}
}
