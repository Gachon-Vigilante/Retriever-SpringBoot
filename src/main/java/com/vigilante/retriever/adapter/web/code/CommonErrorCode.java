package com.vigilante.retriever.adapter.web.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements BaseCode {

	// Client Error (4xx)
	BAD_REQUEST("400", "잘못된 요청", 400),
	UNAUTHORIZED("401", "인증 실패", 401),
	FORBIDDEN("403", "접근 권한 없음", 403),
	NOT_FOUND("404", "리소스를 찾을 수 없음", 404),
	METHOD_NOT_ALLOWED("405", "허용되지 않은 HTTP 메서드", 405),
	CONFLICT("409", "리소스 충돌", 409),
	VALIDATION_ERROR("422", "입력값 검증 실패", 422),
	TOO_MANY_REQUESTS("429", "요청 한도 초과", 429),

	// Server Error (5xx)
	INTERNAL_SERVER_ERROR("500", "내부 서버 오류", 500),
	BAD_GATEWAY("502", "잘못된 게이트웨이", 502),
	SERVICE_UNAVAILABLE("503", "서비스 이용 불가", 503),
	GATEWAY_TIMEOUT("504", "게이트웨이 타임아웃", 504);

	private final String code;
	private final String message;
	private final int httpCode;
}
