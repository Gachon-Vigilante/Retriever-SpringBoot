package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;

public interface ChannelDataMongoPort {

	List<ChannelDataEntity> findAll();

	Optional<ChannelDataEntity> findById(String id);

	// 채널 아이디로 조회
	List<ChannelDataEntity> findByChannelId(long channelId);
}
