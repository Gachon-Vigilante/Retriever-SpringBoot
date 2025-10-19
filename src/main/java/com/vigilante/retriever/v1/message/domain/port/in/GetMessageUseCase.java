package com.vigilante.retriever.v1.message.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;

public interface GetMessageUseCase {

	List<MessageEntity> findAll();

	List<MessageEntity> findByChannelId(Long channelId);
}
