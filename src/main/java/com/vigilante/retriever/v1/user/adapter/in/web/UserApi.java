package com.vigilante.retriever.v1.user.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.GrantRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.UserInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/user")
@Tag(name = "User API", description = "사용자 관리 API")
public interface UserApi {

	@GetMapping
	@Operation(summary = "사용자 목록 조회", description = "전체 사용자 목록을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_GET_USERS_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<UserInfoResponse>>> getUsers();

	@PatchMapping("/grant-role")
	@Operation(summary = "권한 부여", description = "사용자에게 특정 권한을 부여합니다. 관리자 권한이 필요합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_GRANT_ROLE_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {
			@ApiErrorExample.ErrorSpec(code = "403", exampleKey = USER_GRANT_ROLE_403),
			@ApiErrorExample.ErrorSpec(code = "404", exampleKey = USER_GRANT_ROLE_404)
		}
	)
	ResponseEntity<CommonResponse<Void>> grantRole(@AuthenticationPrincipal String adminId,
		@RequestBody GrantRequest grantRequest);
}
