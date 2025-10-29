package com.vigilante.retriever.v1.post.domain.port.in;

import java.util.stream.Stream;

import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

public interface GetPostGraphUseCase {

	Stream<PostGraphView> getAllPost();
}
