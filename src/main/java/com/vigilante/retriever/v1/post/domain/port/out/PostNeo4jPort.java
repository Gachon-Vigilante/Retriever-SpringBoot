package com.vigilante.retriever.v1.post.domain.port.out;

import java.util.Optional;
import java.util.stream.Stream;

import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

public interface PostNeo4jPort {

	PostGraphView save(PostGraphView postGraphView);

	Optional<PostGraphView> findByPostId(String postId);

	Stream<PostGraphView> streamAllWithPromotesAndSimilar();
}
