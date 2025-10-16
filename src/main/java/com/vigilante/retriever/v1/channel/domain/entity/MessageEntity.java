package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.vigilante.retriever.v1.channel.domain.enums.SenderType;

import lombok.Builder;

@Builder
public record MessageEntity(
	String id,
	Long channelId,
	String message,
	int messageId,
	LocalDateTime date,
	LocalDateTime editDate,
	Boolean editHide,
	List<String> entities,
	Integer forwards,
	Long fromId,
	LocalDateTime fwdDate,
	Long fwdFromId,
	String fwdFromName,
	Long groupedId,
	Boolean legacy,
	Media media,
	Boolean mediaUnread,
	Boolean mentioned,
	Boolean out,
	Boolean post,
	List<String> reactions,
	Integer replyToMsgId,
	SenderType senderType,
	Boolean silent,
	LocalDateTime updatedAt,
	Long viaBotId,
	Integer views,
	List<String> argots
) {
	@Builder
	public record Media(
		String url,
		String type
	) {
	}
}
