package com.vigilante.retriever.v1.bookmark.infrastructure.persistence.mongo.document;

import org.springframework.data.mongodb.core.mapping.Document;

import com.vigilante.retriever.global.common.document.BaseDocument;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkDocument extends BaseDocument {

	private String channelId;

	private String userId;
}
