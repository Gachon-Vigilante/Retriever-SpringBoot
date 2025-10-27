package com.vigilante.retriever.v1.user.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.user.application.command.RefreshTokenRedisCommand;
import com.vigilante.retriever.v1.user.domain.port.in.LogoutUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

	private final RefreshTokenRedisCommand refreshTokenRedisCommand;

	@Override
	public void logout(String userId) {
		refreshTokenRedisCommand.deleteByUserId(userId);
	}
}

