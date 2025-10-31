package com.vigilante.retriever.v1.channel.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;

import lombok.Builder;

@Builder
public record ChannelTraceResponse(
	Long channelId,
	String title,
	String username,
	String status,
	Set<PostTrace> promotingPosts,
	Set<ArgotGraphInfoResponse> sellsArgots
) {
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
