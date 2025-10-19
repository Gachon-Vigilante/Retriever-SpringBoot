package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;

public interface ChannelMongoPort {

	List<ChannelEntity> findAll();

	Optional<ChannelEntity> findByChannelId(Long channelId);

	List<ChannelEntity> findByTitleContaining(String title);
}
