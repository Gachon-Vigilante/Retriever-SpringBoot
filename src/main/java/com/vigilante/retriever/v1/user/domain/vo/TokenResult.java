package com.vigilante.retriever.v1.user.domain.vo;

public record TokenResult(
	String accessToken,
	String refreshToken
) {
	public static TokenResult of(String accessToken, String refreshToken) {
		return new TokenResult(accessToken, refreshToken);
	}
}

