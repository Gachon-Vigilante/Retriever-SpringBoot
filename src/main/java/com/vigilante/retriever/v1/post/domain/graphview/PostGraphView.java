package com.vigilante.retriever.v1.post.domain.graphview;

import java.time.LocalDateTime;
import java.util.Set;

import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

import lombok.Builder;

@Builder
public record PostGraphView(
	String postId,
	String title,
	String link,
	String domain,
	String content,
	int cluster,
	LocalDateTime discoveredAt,
	LocalDateTime updatedAt,
	boolean isDeleted,
	Set<Promote> promotesChannels,
	Set<PostGraphView> similarPosts
) {
	@Builder
	public record Promote(
		Long id,
		ChannelGraphView channel
	) {
	}

	public static PostGraphView create(PostGraphView postGraphView) {
		return PostGraphView.builder()
			.postId(postGraphView.postId())
			.title(postGraphView.title())
			.link(postGraphView.link())
			.domain(postGraphView.domain())
			.content(postGraphView.content())
			.cluster(postGraphView.cluster())
			.discoveredAt(postGraphView.discoveredAt())
			.updatedAt(postGraphView.updatedAt())
			.isDeleted(postGraphView.isDeleted())
			.promotesChannels(postGraphView.promotesChannels())
			.similarPosts(postGraphView.similarPosts())
			.build();
	}
}
