package com.vigilante.retriever.v1.post.application.command;

import com.vigilante.retriever.global.common.annotation.CommandService;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.out.PostPersistencePort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PostCommand {

	private final PostPersistencePort postPersistencePort;

	public PostEntity save(PostEntity entity) {
		return postPersistencePort.save(entity);
	}
}
