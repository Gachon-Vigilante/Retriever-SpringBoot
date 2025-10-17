package com.vigilante.retriever.v1.user.application.query;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.user.domain.code.RefreshTokenErrorCode;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.port.out.RefreshTokenRedisPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class RefreshTokenRedisQuery {

	private final RefreshTokenRedisPort refreshTokenRedisPort;

	public RefreshTokenEntity findByTokenValue(String tokenValue) {
		return refreshTokenRedisPort.findByTokenValue(tokenValue).orElseThrow(() -> new NotFoundException(
			RefreshTokenErrorCode.REFRESH_TOKEN_NOT_FOUND));
	}

	public RefreshTokenEntity findByUserId(String userId) {
		return refreshTokenRedisPort.findByUserId(userId)
			.orElseThrow(() -> new NotFoundException(RefreshTokenErrorCode.REFRESH_TOKEN_NOT_FOUND));
	}

	public boolean existsByUserId(String userId) {
		return refreshTokenRedisPort.existsByUserId(userId);
	}
}
