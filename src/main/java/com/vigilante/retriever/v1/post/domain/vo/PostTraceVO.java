package com.vigilante.retriever.v1.post.domain.vo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

import lombok.Builder;

@Builder
public record PostTraceVO(
	String postId,
	String title,
	String link,
	String domain,
	String content,
	int cluster,
	LocalDateTime discoveredAt,
	LocalDateTime updatedAt,
	boolean isDeleted,
	Set<PostTraceVO> similarPosts
) {
	public static Set<PostTraceVO> mapPromotingPosts(Set<PostGraphView> postGraphViews) {
		if (postGraphViews == null || postGraphViews.isEmpty()) {
			return Collections.emptySet();
		}
		return postGraphViews.stream()
			.map(postGraphView -> PostTraceVO.builder()
				.postId(postGraphView.postId())
				.title(postGraphView.title())
				.link(postGraphView.link())
				.domain(postGraphView.domain())
				.content(postGraphView.content())
				.cluster(postGraphView.cluster())
				.discoveredAt(postGraphView.discoveredAt())
				.updatedAt(postGraphView.updatedAt())
				.isDeleted(postGraphView.isDeleted())
				.similarPosts(mapSimilarShallow(postGraphView.similarPosts()))
				.build())
			.collect(Collectors.toSet());
	}

	private static Set<PostTraceVO> mapSimilarShallow(Set<PostGraphView> similarPosts) {
		if (similarPosts == null || similarPosts.isEmpty()) {
			return Collections.emptySet();
		}
		return similarPosts.stream()
			.map(sp -> PostTraceVO.builder()
				.postId(sp.postId())
				.title(sp.title())
				.link(sp.link())
				.domain(sp.domain())
				.content(sp.content())
				.cluster(sp.cluster())
				.discoveredAt(sp.discoveredAt())
				.updatedAt(sp.updatedAt())
				.isDeleted(sp.isDeleted())
				.similarPosts(Collections.emptySet())
				.build())
			.collect(Collectors.toSet());
	}
}
