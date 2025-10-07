package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ChannelSimilarityEntity(
	String id,
	long channelId,
	List<SimilarChannel> similarChannels,
	LocalDateTime updatedAt
) {

	@Builder
	public record SimilarChannel(
		long channelId,
		Double similarity
	) {
	}
}
