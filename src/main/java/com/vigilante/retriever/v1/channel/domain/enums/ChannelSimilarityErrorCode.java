package com.vigilante.retriever.v1.channel.domain.enums;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelSimilarityErrorCode implements BaseCode {
	CHANNEL_SIMILARITY_NOT_FOUND("CHANNEL_SIMILARITY-4041", "해당하는 채널의 유사도 정보를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
