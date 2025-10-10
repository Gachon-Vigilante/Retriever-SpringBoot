package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.adapter;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.post.adapter.out.mapper.PostNeo4jMapper;
import com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node.PostNode;
import com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.repository.PostNeo4jRepository;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.out.PostNeo4jPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostGraphQueryAdapter implements PostNeo4jPort {

	private final PostNeo4jRepository postNeo4JRepository;
	private final PostNeo4jMapper postNeo4jMapper;

	@Override
	public PostGraphView save(PostGraphView postGraphView) {
		PostNode toSave = postNeo4jMapper.toNode(postGraphView);
		PostNode saved = postNeo4JRepository.save(toSave);
		return postNeo4jMapper.toGraphView(saved);
	}

	@Override
	public Optional<PostGraphView> findByPostId(String postId) {
		return postNeo4JRepository.findByPostId(postId).map(postNeo4jMapper::toGraphView);
	}

	@Override
	public Stream<PostGraphView> streamAllWithPromotesAndSimilar() {
		return postNeo4JRepository.streamAllWithPromotesAndSimilar().map(postNeo4jMapper::toGraphView);
	}
}
