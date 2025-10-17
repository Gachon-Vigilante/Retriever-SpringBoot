package com.vigilante.retriever.v1.user.application.command;

import com.vigilante.retriever.common.domain.annotation.CommandService;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.out.UserMongoPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class UserMongoCommand {

	private final UserMongoPort userMongoPort;

	public UserEntity save(UserEntity entity) {
		return userMongoPort.save(entity);
	}

	public void delete(UserEntity entity) {
		userMongoPort.delete(entity);
	}
}
