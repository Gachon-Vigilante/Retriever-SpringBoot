package com.vigilante.retriever.v1.user.domain.dto.command;

public record LoginCommand(
	String loginId,
	String password
) {

	public static LoginCommand of(String loginId, String password) {
		return new LoginCommand(loginId, password);
	}
}

