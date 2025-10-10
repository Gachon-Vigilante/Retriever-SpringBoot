package com.vigilante.retriever.adapter.web.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonSuccessCode implements BaseCode {

	// Success
	SUCCESS("200", "요청이 성공적으로 처리되었습니다", 200),
	RETRIEVED("200", "조회가 완료되었습니다", 200),
	CREATED("201", "생성이 완료되었습니다", 201),
	UPDATED("204", "수정이 완료되었습니다", 204),
	DELETED("204", "삭제가 완료되었습니다", 204);

	private final String code;
	private final String message;
	private final int httpCode;
}
