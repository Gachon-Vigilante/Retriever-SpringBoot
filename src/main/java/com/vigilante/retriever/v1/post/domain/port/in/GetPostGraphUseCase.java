package com.vigilante.retriever.v1.post.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

public interface GetPostGraphUseCase {

	List<PostGraphView> getAllPost();
}
