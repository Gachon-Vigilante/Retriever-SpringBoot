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
import com.vigilante.retriever.v1.user.application.service.GetUserService;
import com.vigilante.retriever.v1.user.application.service.GrantRoleService;
import com.vigilante.retriever.v1.user.domain.dto.command.GrantRoleCommand;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final GetUserService getUserService;
	private final GrantRoleService grantRoleService;
	private final UserWebMapper userWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<UserInfoResponse>>> getUsers() {
		List<UserInfoResponse> users = userWebMapper.toResponseList(getUserService.getAllUsers());
		return ResponseEntity.ok(CommonResponse.retrieved(users));
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> grantRole(@AuthenticationPrincipal String adminId,
		@RequestBody GrantRequest grantRequest) {
		GrantRoleCommand command = userWebMapper.toCommand(adminId, grantRequest);
		grantRoleService.grantRole(command);
		return ResponseEntity.ok(CommonResponse.success());
	}
}
