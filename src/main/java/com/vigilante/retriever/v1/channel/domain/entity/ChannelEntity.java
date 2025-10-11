package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ChannelEntity(
	String _id,
	String title,
	String username,
	Long id,
	String about,
	Long accessHash,
	Boolean broadcast,
	Boolean callActive,
	Boolean callNotEmpty,
	LocalDateTime checkedAt,
	LocalDateTime date,
	Boolean fake,
	Boolean gigaGroup,
	Boolean hasGeo,
	Boolean hasLink,
	LocalDateTime lastMessageDate,
	Boolean left,
	Boolean megaGroup,
	Boolean min,
	Boolean monitoring,
	Boolean noForwards,
	Integer participantsCount,
	String photo,
	Boolean restricted,
	List<String> restrictionReason,
	Boolean scam,
	Boolean signatures,
	Boolean slowModeEnabled,
	String status,
	LocalDateTime updatedAt,
	Boolean verified
) {
}
