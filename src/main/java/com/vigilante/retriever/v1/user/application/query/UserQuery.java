package com.vigilante.retriever.v1.user.application.query;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.exception.UserNotFoundException;
import com.vigilante.retriever.v1.user.domain.port.out.UserPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class UserQuery {

	private final UserPersistencePort userPersistencePort;

	public UserEntity findByLoginId(String id) {
		return userPersistencePort.findByLoginId(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public boolean existsByLoginId(String loginId) {
		return userPersistencePort.existsByLoginId(loginId);
	}
}
