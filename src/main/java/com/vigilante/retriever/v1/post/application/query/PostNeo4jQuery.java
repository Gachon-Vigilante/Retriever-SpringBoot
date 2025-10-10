package com.vigilante.retriever.v1.post.application.query;

import java.util.Optional;
import java.util.stream.Stream;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.out.PostNeo4jPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class PostNeo4jQuery {

	private final PostNeo4jPort postNeo4jPort;

	public Optional<PostGraphView> findByPostId(String postId) {
		return postNeo4jPort.findByPostId(postId);
	}

	public Stream<PostGraphView> streamAllWithPromotesAndSimilar() {
		return postNeo4jPort.streamAllWithPromotesAndSimilar();
	}
}
