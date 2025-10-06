package com.vigilante.retriever.v1.channel.infrastructure.persistence.document;

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
@Document(collection = "channel_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelDataDocument {

	@Id
	private String _id;

	private long channelId;

	private LocalDateTime timestamp;

	private String text;

	private SenderInfo sender;

	private Integer views;

	private String url;

	private int id;

	private Media media;

	private List<String> argot;

	private List<String> drugs;

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class SenderInfo {

		private String type;

		private String name;

		@Field("senderId")
		private long senderId;
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Media {

		private String url;

		private String type;
	}
}
