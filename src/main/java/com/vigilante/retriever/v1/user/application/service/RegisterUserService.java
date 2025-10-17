package com.vigilante.retriever.v1.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.common.domain.exception.ConflictException;
import com.vigilante.retriever.v1.user.application.command.UserMongoCommand;
import com.vigilante.retriever.v1.user.application.query.UserMongoQuery;
import com.vigilante.retriever.v1.user.domain.code.UserErrorCode;
import com.vigilante.retriever.v1.user.domain.dto.command.RegisterUserCommand;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.in.RegisterUserUseCase;
import com.vigilante.retriever.v1.user.domain.port.out.PasswordEncoderPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

	private final UserMongoQuery userMongoQuery;
	private final UserMongoCommand userMongoCommand;
	private final PasswordEncoderPort passwordEncoderPort;

	@Override
	@Transactional
	public void signUp(RegisterUserCommand command) {
		if (userMongoQuery.existsByLoginId(command.loginId())) {
			throw new ConflictException(UserErrorCode.USER_DUPLICATED);
		}

		UserEntity user = UserEntity.create(command.loginId(), passwordEncoderPort.encode(command.password()),
			command.name(), Role.USER);

		userMongoCommand.save(user);
	}
}

