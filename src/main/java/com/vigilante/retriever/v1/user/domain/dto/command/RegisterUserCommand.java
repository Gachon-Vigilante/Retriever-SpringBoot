package com.vigilante.retriever.v1.user.domain.dto.command;

public record RegisterUserCommand(
	String loginId,
	String password,
	String name
) {
	public static RegisterUserCommand of(String loginId, String password, String name) {
		return new RegisterUserCommand(loginId, password, name);
	}
}

