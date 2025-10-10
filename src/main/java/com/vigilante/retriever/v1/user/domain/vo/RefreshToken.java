package com.vigilante.retriever.v1.user.domain.vo;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record RefreshToken(
	String userId,
	String tokenValue,
	LocalDateTime issuedAt,
	LocalDateTime expiresAt
) {

	public static RefreshToken create(String userId, String tokenValue, long ttlSeconds) {
		LocalDateTime now = LocalDateTime.now();
		return RefreshToken.builder()
			.userId(userId)
			.tokenValue(tokenValue)
			.issuedAt(now)
			.expiresAt(now.plusSeconds(ttlSeconds))
			.build();
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiresAt);
	}
}

