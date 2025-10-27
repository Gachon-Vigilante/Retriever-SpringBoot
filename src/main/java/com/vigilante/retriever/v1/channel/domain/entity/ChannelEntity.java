package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.vigilante.retriever.v1.channel.domain.enums.ChannelStatus;

import lombok.Builder;

@Builder
public record ChannelEntity(
	String id,
	String title,
	String username,
	Long channelId,
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
	List<RestrictionReason> restrictionReason,
	Boolean scam,
	Boolean signatures,
	Boolean slowModeEnabled,
	ChannelStatus status,
	LocalDateTime updatedAt,
	Boolean verified,
	Catalog catalog
) {
	@Builder
	public record RestrictionReason(
		String platform,
		String reason,
		String text
	) {
	}

	@Builder
	public record Catalog(
		List<Integer> messageIds,
		String summary
	) {
	}
}
