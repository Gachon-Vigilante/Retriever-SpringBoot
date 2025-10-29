package com.vigilante.retriever.v1.post.application.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.post.application.query.PostNeo4jQuery;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostGraphUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPostGraphService implements GetPostGraphUseCase {

	private final PostNeo4jQuery postNeo4jQuery;

	@Override
	public Stream<PostGraphView> getAllPost() {
		return postNeo4jQuery.streamAllWithPromotesAndSimilar();
	}
}
