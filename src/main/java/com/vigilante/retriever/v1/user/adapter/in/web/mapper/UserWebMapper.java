package com.vigilante.retriever.v1.user.adapter.in.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.GrantRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.LoginRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.SignUpRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.LoginResponse;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.UserInfoResponse;
import com.vigilante.retriever.v1.user.domain.dto.command.GrantRoleCommand;
import com.vigilante.retriever.v1.user.domain.dto.command.LoginCommand;
import com.vigilante.retriever.v1.user.domain.dto.command.RegisterUserCommand;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.vo.LoginResult;

@Component
public class UserWebMapper {

	public LoginCommand toCommand(LoginRequest request) {
		return LoginCommand.of(
			request.loginId(),
			request.password()
		);
	}

	public RegisterUserCommand toCommand(SignUpRequest request) {
		return RegisterUserCommand.of(
			request.loginId(),
			request.password(),
			request.name()
		);
	}

	public GrantRoleCommand toCommand(String adminId, GrantRequest request) {
		return GrantRoleCommand.of(
			adminId,
			request.loginId(),
			request.role()
		);
	}

	public LoginResponse toResponse(LoginResult result) {
		return LoginResponse.of(
			result.name(),
			result.role()
		);
	}

	public UserInfoResponse toResponse(UserEntity entity) {
		return UserInfoResponse.of(
			entity.loginId(),
			entity.name(),
			entity.role()
		);
	}

	public List<UserInfoResponse> toResponseList(List<UserEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}
}

