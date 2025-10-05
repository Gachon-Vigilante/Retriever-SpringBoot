package com.vigilante.retriever.v1.user.application.command;

import com.vigilante.retriever.global.common.annotation.CommandService;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.out.UserPersistencePort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class UserCommand {

	private final UserPersistencePort userPersistencePort;

	public UserEntity save(UserEntity entity) {
		return userPersistencePort.save(entity);
	}
}
