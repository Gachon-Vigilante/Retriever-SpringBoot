package com.vigilante.retriever.v1.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.common.domain.exception.BadRequestException;
import com.vigilante.retriever.common.domain.exception.ForbiddenException;
import com.vigilante.retriever.v1.user.application.command.UserMongoCommand;
import com.vigilante.retriever.v1.user.application.query.UserMongoQuery;
import com.vigilante.retriever.v1.user.domain.code.UserErrorCode;
import com.vigilante.retriever.v1.user.domain.dto.command.GrantRoleCommand;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.in.GrantRoleUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrantRoleService implements GrantRoleUseCase {

	private final UserMongoQuery userMongoQuery;
	private final UserMongoCommand userMongoCommand;

	@Override
	@Transactional
	public void grantRole(GrantRoleCommand command) {
		if (command.role() == Role.ROOT) {
			throw new BadRequestException(UserErrorCode.CAN_NOT_GRANT_ROOT_ROLE);
		}

		UserEntity user = userMongoQuery.findByLoginId(command.loginId());

		if (user.id().equals(command.adminId())) {
			throw new ForbiddenException(UserErrorCode.CAN_NOT_CHANGE_MY_ROLE);
		}

		if (user.role() == command.role()) {
			throw new BadRequestException(UserErrorCode.ALREADY_GRANTED_ROLE);
		}

		UserEntity updatedUser = user.updateRole(command.role());
		userMongoCommand.save(updatedUser);

		log.info("loginId: {} 에게 {} 역할을 부여했습니다.", command.loginId(), command.role());
	}
}

