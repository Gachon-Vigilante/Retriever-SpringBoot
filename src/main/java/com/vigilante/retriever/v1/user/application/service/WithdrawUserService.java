package com.vigilante.retriever.v1.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vigilante.retriever.v1.user.application.command.RefreshTokenRedisCommand;
import com.vigilante.retriever.v1.user.application.command.UserMongoCommand;
import com.vigilante.retriever.v1.user.application.query.RefreshTokenRedisQuery;
import com.vigilante.retriever.v1.user.application.query.UserMongoQuery;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.in.WithdrawUserUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawUserService implements WithdrawUserUseCase {

	private final UserMongoQuery userMongoQuery;
	private final UserMongoCommand userMongoCommand;
	private final RefreshTokenRedisQuery refreshTokenRedisQuery;
	private final RefreshTokenRedisCommand refreshTokenRedisCommand;

	@Override
	@Transactional
	public void withdraw(String loginId) {
		UserEntity user = userMongoQuery.findByLoginId(loginId);

		String userId = user.id();

		// 사용자 삭제
		userMongoCommand.delete(user);

		if (refreshTokenRedisQuery.existsByUserId(userId)) {
			refreshTokenRedisCommand.deleteByUserId(userId);
		}

		log.info("회원 탈퇴가 완료되었습니다. loginId: {}", loginId);
	}
}

