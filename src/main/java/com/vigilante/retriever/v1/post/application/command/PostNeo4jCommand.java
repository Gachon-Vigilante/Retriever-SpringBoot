package com.vigilante.retriever.v1.post.application.command;

import com.vigilante.retriever.global.common.annotation.CommandService;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.out.PostNeo4jPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PostNeo4jCommand {

	private final PostNeo4jPort postNeo4jPort;

	public PostGraphView save(PostGraphView entity) {
		return postNeo4jPort.save(entity);
	}
}
