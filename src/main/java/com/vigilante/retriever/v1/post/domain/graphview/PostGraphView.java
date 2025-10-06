package com.vigilante.retriever.v1.post.domain.graphview;

import java.util.Set;

import lombok.Builder;

@Builder
public record PostGraphView(
	String postId,
	int cluster,
	String content,
	String link,
	String siteName,
	String createdAt,
	String updatedAt,
	Set<PromoteGraphView> promotesChannels,
	Set<PostGraphView> similarPosts
) {
}
