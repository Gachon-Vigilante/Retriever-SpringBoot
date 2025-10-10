package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.vigilante.retriever.v1.user.domain.vo.RefreshToken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash(value = "refreshToken", timeToLive = 1209600)
public class RefreshTokenRedisEntity {

	@Id
	private String userId;

	@Indexed
	private String refreshToken;

	private LocalDateTime issuedAt;

	private LocalDateTime expiresAt;

	public static RefreshTokenRedisEntity from(RefreshToken domain) {
		return RefreshTokenRedisEntity.builder()
			.userId(domain.userId())
			.refreshToken(domain.tokenValue())
			.issuedAt(domain.issuedAt())
			.expiresAt(domain.expiresAt())
			.build();
	}

	public RefreshToken toDomain() {
		return RefreshToken.builder()
			.userId(userId)
			.tokenValue(refreshToken)
			.issuedAt(issuedAt)
			.expiresAt(expiresAt)
			.build();
	}
}

