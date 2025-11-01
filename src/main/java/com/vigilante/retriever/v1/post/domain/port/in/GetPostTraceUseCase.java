package com.vigilante.retriever.v1.post.domain.port.in;

import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

public interface GetPostTraceUseCase {

	PostGraphView getPostTrace(String postId);
}
