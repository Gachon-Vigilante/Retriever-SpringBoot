package com.vigilante.retriever.v1.message.domain.port.out;

import java.util.List;

import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;

public interface MessageMongoPort {

	List<MessageEntity> findAll();

	// 채널 아이디로 조회
	List<MessageEntity> findByChannelId(Long channelId);
}
