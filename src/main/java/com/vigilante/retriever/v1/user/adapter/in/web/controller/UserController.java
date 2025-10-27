package com.vigilante.retriever.v1.user.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.user.adapter.in.web.UserApi;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.GrantRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.UserInfoResponse;
import com.vigilante.retriever.v1.user.adapter.in.web.mapper.UserWebMapper;
import com.vigilante.retriever.v1.user.domain.dto.command.GrantRoleCommand;
import com.vigilante.retriever.v1.user.domain.port.in.GetUserUseCase;
import com.vigilante.retriever.v1.user.domain.port.in.GrantRoleUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final GetUserUseCase getUserUseCase;
	private final GrantRoleUseCase grantRoleUseCase;
	private final UserWebMapper userWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<UserInfoResponse>>> findAll() {
		List<UserInfoResponse> users = userWebMapper.toResponseList(getUserUseCase.findAll());
		return CommonResponse.retrieved(users);
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> grantRole(@AuthenticationPrincipal String adminId,
		@RequestBody GrantRequest grantRequest) {
		GrantRoleCommand command = userWebMapper.toCommand(adminId, grantRequest);
		grantRoleUseCase.grantRole(command);
		return CommonResponse.success();
	}
}
