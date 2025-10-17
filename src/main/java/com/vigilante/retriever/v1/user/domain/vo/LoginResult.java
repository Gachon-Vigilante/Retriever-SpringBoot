package com.vigilante.retriever.v1.user.domain.vo;

public record LoginResult(
	String accessToken,
	String refreshToken,
	String name,
	String role
) {
	public static LoginResult of(String accessToken, String refreshToken, String name, String role) {
		return new LoginResult(accessToken, refreshToken, name, role);
	}
}

