package com.vigilante.retriever.v1.post.adapter.out.persistence.mongo.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.vigilante.retriever.infrastructure.common.document.BaseDocument;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDocument extends BaseDocument {

	private String link;

	private String tag;

	private String siteName;

	private String title;

	private String content;

	private String source;

	private List<String> promoSiteLink;

	private List<String> promoChannelId; // promoSiteName -> promoChannelId

	@Field("cluster_label")
	private int clusterLabel;

	private String author;

	private boolean deleted;

	private LocalDateTime timestamp;

	private LocalDateTime deletedAt;
}
