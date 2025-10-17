package com.vigilante.retriever.v1.user.domain.dto.command;

import com.vigilante.retriever.common.domain.enums.Role;

public record GrantRoleCommand(
	String adminId,
	String loginId,
	Role role
) {
	public static GrantRoleCommand of(String adminId, String loginId, Role role) {
		return new GrantRoleCommand(adminId, loginId, role);
	}
}

