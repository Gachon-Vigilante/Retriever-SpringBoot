package com.vigilante.retriever.v1.channel.domain.enums;

import com.vigilante.retriever.global.common.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelInfoErrorCode implements BaseCode {
	CHANNEL_INFO_NOT_FOUND("CHANNEL_INFO-4041", "해당하는 채널 정보를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
