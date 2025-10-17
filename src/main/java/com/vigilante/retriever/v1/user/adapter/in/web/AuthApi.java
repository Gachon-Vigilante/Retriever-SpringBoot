package com.vigilante.retriever.v1.user.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.DisableSwaggerSecurity;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.LoginRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.SignUpRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@Tag(name = "Auth API", description = "사용자 인증/인가 API")
public interface AuthApi {

	@DisableSwaggerSecurity
	@PostMapping("/signup")
	@Operation(summary = "회원 가입", description = "사용자 회원 가입을 처리합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "201", exampleKey = USER_CREATE_201)})
	@ApiErrorExample(
		include = {"500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "409", exampleKey = USER_CREATE_409)})
	ResponseEntity<CommonResponse<Void>> signUp(@RequestBody SignUpRequest signUpRequest);

	@DisableSwaggerSecurity
	@PostMapping("/login")
	@Operation(summary = "로그인", description = "사용자 로그인을 처리합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_LOGIN_200)})
	@ApiErrorExample(
		include = {"500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "401", exampleKey = USER_LOGIN_401),
			@ApiErrorExample.ErrorSpec(code = "404", exampleKey = USER_LOGIN_404)}
	)
	ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest,
		HttpServletResponse httpServletResponse);

	@DeleteMapping("/logout")
	@Operation(summary = "로그아웃", description = "사용자 로그아웃을 처리합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_LOGOUT_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<Void>> logout(@AuthenticationPrincipal String userId,
		HttpServletResponse httpServletResponse);

	@PostMapping("/reissue")
	@Operation(summary = "토큰 재발급", description = "리프레시 토큰을 사용하여 새로운 액세스 토큰과 리프레시 토큰을 발급합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_REISSUE_200)})
	@ApiErrorExample(
		include = {"500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "401", exampleKey = USER_REISSUE_401)}
	)
	ResponseEntity<CommonResponse<Void>> reissueToken(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

	@DeleteMapping("/withdraw")
	@Operation(summary = "회원 탈퇴", description = "사용자 회원 탈퇴를 처리합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = USER_WITHDRAW_200)})
	@ApiErrorExample(include = {"404", "500"})
	ResponseEntity<CommonResponse<Void>> withdraw(@RequestParam String loginId);
}
