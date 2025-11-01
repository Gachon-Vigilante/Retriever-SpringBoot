package com.vigilante.retriever.v1.post.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.post.application.query.PostNeo4jQuery;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostTraceUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPostTraceService implements GetPostTraceUseCase {

	private final PostNeo4jQuery postNeo4jQuery;

	@Override
	public PostGraphView getPostTrace(String postId) {
		PostGraphView post = postNeo4jQuery.findPostWithAllRelations(postId);
		return PostGraphView.create(post);
	}
}
