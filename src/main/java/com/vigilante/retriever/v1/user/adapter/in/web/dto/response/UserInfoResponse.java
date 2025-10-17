package com.vigilante.retriever.v1.user.adapter.in.web.dto.response;

import com.vigilante.retriever.common.domain.enums.Role;

public record UserInfoResponse(
	String loginId,
	String name,
	Role role
) {
	public static UserInfoResponse of(String loginId, String name, Role role) {
		return new UserInfoResponse(loginId, name, role);
	}
}
