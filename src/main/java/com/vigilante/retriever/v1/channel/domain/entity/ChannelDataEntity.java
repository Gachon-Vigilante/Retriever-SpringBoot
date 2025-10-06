package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ChannelDataEntity(
	String _id,
	long channelId,
	LocalDateTime timestamp,
	String text,
	SenderInfo sender,
	Integer views,
	String url,
	int id,
	Media media,
	List<String> argot,
	List<String> drugs
) {

	@Builder
	public record SenderInfo(
		String type,
		String name,
		long senderId
	) {
	}

	@Builder
	public record Media(
		String url,
		String type
	) {
	}
}
