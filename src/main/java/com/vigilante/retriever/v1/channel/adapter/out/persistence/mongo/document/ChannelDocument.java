package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "channels")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelDocument {

	@Id
	private String id;

	private String title;

	private String username;

	@Field("channel_id")
	private Long channelId;

	private String about;

	@Field("access_hash")
	private Long accessHash;

	private Boolean broadcast;

	@Field("call_active")
	private Boolean callActive;

	@Field("call_not_empty")
	private Boolean callNotEmpty;

	@Field("checked_at")
	private LocalDateTime checkedAt;

	// 텔레그램 채널이 생성된 날짜 및 시간
	private LocalDateTime date;

	private Boolean fake;

	@Field("gigagroup")
	private Boolean gigaGroup;

	@Field("has_geo")
	private Boolean hasGeo;

	@Field("has_link")
	private Boolean hasLink;

	@Field("last_message_date")
	private LocalDateTime lastMessageDate;

	private Boolean left;

	@Field("megagroup")
	private Boolean megaGroup;

	private Boolean min;

	private Boolean monitoring;

	@Field("noforwards")
	private Boolean noForwards;

	@Field("participants_count")
	private Integer participantsCount;

	private String photo;

	private Boolean restricted;

	@Field("restriction_reason")
	private List<String> restrictionReason;

	private Boolean scam;

	private Boolean signatures;

	@Field("slowmode_enabled")
	private Boolean slowModeEnabled;

	private String status;

	@Field("updated_at")
	private LocalDateTime updatedAt;

	private Boolean verified;
}
