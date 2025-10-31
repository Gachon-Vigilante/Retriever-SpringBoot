package com.vigilante.retriever.v1.channel.adapter.in.web.dto.response;

import java.util.Set;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostTraceResponse;

import lombok.Builder;

@Builder
public record ChannelTraceResponse(
	Long channelId,
	String title,
	String username,
	String status,
	Set<PostTraceResponse> promotingPosts,
	Set<ArgotGraphInfoResponse> sellsArgots
) {
}
