package com.vigilante.retriever.v1.channel.infrastructure.persistence.document;

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
@Document(collection = "channel_similarity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelSimilarityDocument {

	@Id
	private String id;

	private long channelId;

	private List<SimilarChannel> similarChannels;

	@Field("timestamp")
	private LocalDateTime updatedAt;

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class SimilarChannel {

		private long channelId;

		private Double similarity;
	}
}
