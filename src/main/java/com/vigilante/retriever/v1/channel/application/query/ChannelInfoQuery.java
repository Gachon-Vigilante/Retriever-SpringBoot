package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelInfoEntity;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelDataNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelInfoPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelInfoQuery {

	private final ChannelInfoPersistencePort channelInfoPersistencePort;

	public List<ChannelInfoEntity> findAll() {
		return channelInfoPersistencePort.findAll();
	}

	public ChannelInfoEntity getById(String id) {
		return channelInfoPersistencePort.findById(id)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public ChannelInfoEntity getByLink(String link) {
		return channelInfoPersistencePort.findByLink(link)
			.orElseThrow(ChannelDataNotFoundException::new);
	}

	public List<ChannelInfoEntity> findByTitleContaining(String title) {
		return channelInfoPersistencePort.findByTitleContaining(title);
	}

	public boolean existsById(String id) {
		return channelInfoPersistencePort.existsById(id);
	}
}
