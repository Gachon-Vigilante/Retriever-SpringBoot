package com.vigilante.retriever.v1.post.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelGraphInfoResponse;

import lombok.Builder;

@Builder
public record PostGraphInfoResponse(
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
	Set<PostGraphInfoResponse> similarPosts
) {
	@Builder
	public record Promote(
		Long id,
		ChannelGraphInfoResponse channel
	) {
	}
}
