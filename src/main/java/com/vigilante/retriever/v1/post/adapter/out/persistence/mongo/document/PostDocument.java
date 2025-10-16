package com.vigilante.retriever.v1.post.adapter.out.persistence.mongo.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
@Document(collection = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDocument {

	@Id
	private String id;

	private String link;

	private Analysis analysis;

	@Field("analysis_job_id")
	private String analysisJobId;

	private String description;

	@Field("discovered_at")
	private LocalDateTime discoveredAt;

	private String domain;

	private String html;

	@Field("published_at")
	private LocalDateTime publishedAt;

	private String text;

	private String title;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	private List<Similarity> similarities;

	@Field("site_name")
	private String siteName;

	private Long cluster;

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Analysis {

		@Field("drugs_related")
		private boolean drugsRelated;

		private List<Promotion> promotions;
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Promotion {

		private String content;

		private List<Identifier> identifiers;
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Identifier {

		private String identifier;

		@Field("channel_id")
		private String channelId;

		@Field("is_processed")
		private boolean isProcessed;

		private String error;
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Similarity {

		@Field("post_id")
		private String postId;

		private double similarity;
	}
}
