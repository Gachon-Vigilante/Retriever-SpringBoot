package com.vigilante.retriever.v1.bookmark.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements BaseCode {
	BOOKMARK_NOT_FOUND("BOOKMARK-4041", "해당하는 채널 북마크를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
