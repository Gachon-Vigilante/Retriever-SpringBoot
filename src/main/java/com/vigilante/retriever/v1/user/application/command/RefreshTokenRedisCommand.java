package com.vigilante.retriever.v1.user.application.command;

import com.vigilante.retriever.common.domain.annotation.CommandService;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.port.out.RefreshTokenRedisPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RefreshTokenRedisCommand {

	private final RefreshTokenRedisPort refreshTokenRedisPort;

	public RefreshTokenEntity save(RefreshTokenEntity token) {
		return refreshTokenRedisPort.save(token);
	}

	public void deleteByUserId(String userId) {
		refreshTokenRedisPort.deleteByUserId(userId);
	}
}
