package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.MessageEntity;

public interface MessageMongoPort {

	List<MessageEntity> findAll();

	Optional<MessageEntity> findById(String id);

	// 채널 아이디로 조회
	List<MessageEntity> findByChannelId(long channelId);
}
