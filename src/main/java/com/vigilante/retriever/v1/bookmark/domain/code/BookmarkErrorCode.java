package com.vigilante.retriever.v1.bookmark.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements BaseCode {
	BOOKMARK_NOT_FOUND("BOOKMARK-4041", "해당하는 채널 북마크를 찾을 수 없습니다."),
	CANNOT_DELETE_OTHERS_BOOKMARK("BOOKMARK-4031", "자신의 북마크만 삭제할 수 있습니다."),
	BOOKMARK_ALREADY_EXISTS("BOOKMARK-4091", "이미 추가된 북마크입니다.");

	private final String code;
	private final String message;
}
