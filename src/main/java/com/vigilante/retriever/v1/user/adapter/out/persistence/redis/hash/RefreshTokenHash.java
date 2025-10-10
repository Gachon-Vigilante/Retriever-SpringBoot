package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.hash;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

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
public class RefreshTokenHash {

	@Id
	private String userId;

	@Indexed
	private String refreshToken;

	private LocalDateTime issuedAt;

	private LocalDateTime expiresAt;
}

