package com.vigilante.retriever.v1.post.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseCode {
	POST_NOT_FOUND("POST-4041", "해당하는 게시글을 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
