package com.vigilante.retriever.v1.user.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record RefreshTokenEntity(
	String userId,
	String refreshToken,
	LocalDateTime issuedAt,
	LocalDateTime expiresAt
) {
}
