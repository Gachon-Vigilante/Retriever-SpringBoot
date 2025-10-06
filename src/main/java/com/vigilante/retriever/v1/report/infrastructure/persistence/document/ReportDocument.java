package com.vigilante.retriever.v1.report.infrastructure.persistence.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "reports")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportDocument {

	@Id
	private String id;

	private long channelId;

	private int chatId;

	private String type;

	private String content;

	private String description;

	private LocalDateTime timestamp;
}
