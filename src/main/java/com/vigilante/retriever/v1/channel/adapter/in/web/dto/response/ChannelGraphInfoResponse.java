package com.vigilante.retriever.v1.channel.adapter.in.web.dto.response;

import java.util.Set;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;

import lombok.Builder;

@Builder
public record ChannelGraphInfoResponse(
	String id,
	String title,
	String username,
	String status,
	int promotedCount,
	Set<ArgotGraphInfoResponse> sellsArgots
) {
}
