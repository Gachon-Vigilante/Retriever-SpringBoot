package com.vigilante.retriever.v1.channel.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;

public interface GetChannelUseCase {

	List<ChannelEntity> findAll();

	ChannelEntity getByChannelId(Long ChannelId);

	List<ChannelEntity> findByTitleContaining(String title);
}
