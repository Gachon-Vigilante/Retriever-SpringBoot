package com.vigilante.retriever.v1.user.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseCode {

	ALREADY_GRANTED_ROLE("USER-4001", "이미 해당 역할이 부여된 사용자입니다"),
	PASSWORD_MISMATCH("USER-4011", "비밀번호가 일치하지 않습니다."),
	CAN_NOT_CHANGE_MY_ROLE("USER-4031", "자신의 권한은 변경할 수 없습니다."),
	CAN_NOT_GRANT_ROOT_ROLE("USER-4032", "ROOT 권한은 부여할 수 없습니다."),
	USER_NOT_FOUND("USER-4041", "사용자를 찾을 수 없습니다."),
	USER_DUPLICATED("USER-4091", "이미 존재하는 사용자입니다.");

	private final String code;
	private final String message;
}
