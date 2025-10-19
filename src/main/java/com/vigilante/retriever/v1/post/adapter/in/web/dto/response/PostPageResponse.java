package com.vigilante.retriever.v1.post.adapter.in.web.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record PostPageResponse(
	Long totalCount,
	List<PostInfoResponse> posts
) {
}
