package com.vigilante.retriever.v1.post.domain.graphview;

import java.time.LocalDateTime;
import java.util.Set;

import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

import lombok.Builder;

@Builder
public record PostGraphView(
	String postId,
	int cluster,
	String link,
	String content,
	String title,
	String domain,
	String siteName,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	Set<Promote> promotesChannels,
	Set<PostGraphView> similarPosts
) {
	@Builder
	public record Promote(
		Long id,
		ChannelGraphView channel
	) {
	}
}
