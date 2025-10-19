package com.vigilante.retriever.v1.post.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelGraphInfoResponse;

import lombok.Builder;

@Builder
public record PostGraphInfoResponse(
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
	Set<PostGraphInfoResponse> similarPosts
) {
	@Builder
	public record Promote(
		Long id,
		ChannelGraphInfoResponse channel
	) {
	}
}
