package com.vigilante.retriever.v1.user.application.query;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.exception.UserNotFoundException;
import com.vigilante.retriever.v1.user.domain.port.out.UserMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class UserMongoQuery {

	private final UserMongoPort userMongoPort;

	public UserEntity findByLoginId(String id) {
		return userMongoPort.findByLoginId(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public boolean existsByLoginId(String loginId) {
		return userMongoPort.existsByLoginId(loginId);
	}
}
