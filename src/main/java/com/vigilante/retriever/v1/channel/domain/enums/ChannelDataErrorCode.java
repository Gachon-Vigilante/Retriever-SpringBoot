package com.vigilante.retriever.v1.channel.domain.enums;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelDataErrorCode implements BaseCode {
	CHANNEL_DATA_NOT_FOUND("CHANNEL_DATA-4041", "해당하는 채널 데이터를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
