package com.vigilante.retriever.v1.channel.domain.vo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

import lombok.Builder;

@Builder
public record ChannelTraceVO(
	Long channelId,
	String title,
	String username,
	String status,
	Set<PostTrace> promotingPosts,
	Set<ArgotGraphView> sellsArgots
) {
	public static ChannelTraceVO create(ChannelGraphView channelGraphView) {
		return ChannelTraceVO.builder()
			.channelId(channelGraphView.channelId())
			.title(channelGraphView.title())
			.username(channelGraphView.username())
			.status(channelGraphView.status())
			.promotingPosts(mapPromotingPosts(channelGraphView.promotedByPosts()))
			.sellsArgots(channelGraphView.sellsArgots())
			.build();
	}

	private static Set<PostTrace> mapPromotingPosts(Set<PostGraphView> postGraphViews) {
		if (postGraphViews == null || postGraphViews.isEmpty()) {
			return Collections.emptySet();
		}
		return postGraphViews.stream()
			.map(postGraphView -> PostTrace.builder()
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

	private static Set<PostTrace> mapSimilarShallow(Set<PostGraphView> similarPosts) {
		if (similarPosts == null || similarPosts.isEmpty()) {
			return Collections.emptySet();
		}
		return similarPosts.stream()
			.map(sp -> PostTrace.builder()
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

	@Builder
	public record PostTrace(
		String postId,
		String title,
		String link,
		String domain,
		String content,
		int cluster,
		LocalDateTime discoveredAt,
		LocalDateTime updatedAt,
		boolean isDeleted,
		Set<PostTrace> similarPosts
	) {
	}
}
