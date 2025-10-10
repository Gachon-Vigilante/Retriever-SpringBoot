package com.vigilante.retriever.v1.post.application.command;

import com.vigilante.retriever.common.domain.annotation.CommandService;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.out.PostMongoPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PostMongoCommand {

	private final PostMongoPort postMongoPort;

	public PostEntity save(PostEntity entity) {
		return postMongoPort.save(entity);
	}
}
