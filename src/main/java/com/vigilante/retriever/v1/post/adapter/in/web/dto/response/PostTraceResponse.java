package com.vigilante.retriever.v1.post.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;

@Builder
public record PostTraceResponse(
	String postId,
	String title,
	String link,
	String domain,
	String content,
	int cluster,
	LocalDateTime discoveredAt,
	LocalDateTime updatedAt,
	boolean isDeleted,
	Set<PostTraceResponse> similarPosts
) {
}
