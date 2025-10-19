package com.vigilante.retriever.v1.channel.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.channel.application.query.ChannelMongoQuery;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;
import com.vigilante.retriever.v1.channel.domain.port.in.GetChannelUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetChannelService implements GetChannelUseCase {

	private final ChannelMongoQuery channelMongoQuery;

	@Override
	public List<ChannelEntity> findAll() {
		return channelMongoQuery.findAll();
	}

	@Override
	public ChannelEntity getByChannelId(Long ChannelId) {
		return channelMongoQuery.getByChannelId(ChannelId);
	}

	@Override
	public List<ChannelEntity> findByTitleContaining(String title) {
		return channelMongoQuery.findByTitleContaining(title);
	}
}
