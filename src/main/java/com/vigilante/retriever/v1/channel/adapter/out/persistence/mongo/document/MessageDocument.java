package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "messages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageDocument {

	@Id
	private String id;

	@Field("channel_id")
	private Long channelId;

	private String message;

	@Field("message_id")
	private int messageId;

	// 최초 게시 시간
	private LocalDateTime date;

	// 마지막 수정 시간
	@Field("edit_date")
	private LocalDateTime editDate;

	@Field("edit_hide")
	private Boolean editHide;

	// 텍스트 내 엔티티(링크/멘션 등) 목록
	private List<String> entities;

	// 전달(포워딩) 횟수
	private Integer forwards;

	@Field("from_id")
	private Long fromId;

	@Field("fwd_date")
	private LocalDateTime fwdDate;

	@Field("fwd_from_id")
	private Long fwdFromId;

	@Field("fwd_from_name")
	private String fwdFromName;

	@Field("grouped_id")
	private Long groupedId;

	private Boolean legacy;

	private Media media;

	@Field("media_unread")
	private Boolean mediaUnread;

	private Boolean mentioned;

	private Boolean out;

	private Boolean post;

	private List<String> reactions;

	@Field("reply_to_msg_id")
	private Integer replyToMsgId;

	@Field("sender_type")
	private String senderType;

	private Boolean silent;

	@Field("updated_at")
	private LocalDateTime updatedAt;

	@Field("via_bot_id")
	private Long viaBotId;

	private Integer views;

	private List<String> argots;

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Media {

		private String url;

		private String type;
	}
}
