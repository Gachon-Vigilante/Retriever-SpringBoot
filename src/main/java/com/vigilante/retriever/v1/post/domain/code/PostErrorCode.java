package com.vigilante.retriever.v1.post.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseCode {

	FAIL_UPDATE_POST_ID("POST-4001", "게시글 ID 업데이트에 실패했습니다."),
	POST_NOT_FOUND("POST-4041", "해당하는 게시글을 찾을 수 없습니다."),
	ALREADY_EXIST_PROMOTE_RELATION("POST-4091", "이미 홍보 관계가 존재합니다.");

	private final String code;
	private final String message;
}
