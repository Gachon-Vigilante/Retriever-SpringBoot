package com.vigilante.retriever.v1.user.application.query;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.exception.RefreshTokenNotFoundException;
import com.vigilante.retriever.v1.user.domain.port.out.RefreshTokenRedisPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class RefreshTokenRedisQuery {

	private final RefreshTokenRedisPort refreshTokenRedisPort;

	public RefreshTokenEntity findByTokenValue(String tokenValue) {
		return refreshTokenRedisPort.findByTokenValue(tokenValue).orElseThrow(RefreshTokenNotFoundException::new);
	}

	public RefreshTokenEntity findByUserId(String userId) {
		return refreshTokenRedisPort.findByUserId(userId).orElseThrow(RefreshTokenNotFoundException::new);
	}

	public boolean existsByUserId(String userId) {
		return refreshTokenRedisPort.existsByUserId(userId);
	}
}
