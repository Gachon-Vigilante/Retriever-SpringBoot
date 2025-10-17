package com.vigilante.retriever.v1.user.adapter.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.infrastructure.auth.cookie.CookieProvider;
import com.vigilante.retriever.v1.user.adapter.in.web.AuthApi;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.LoginRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.request.SignUpRequest;
import com.vigilante.retriever.v1.user.adapter.in.web.dto.response.LoginResponse;
import com.vigilante.retriever.v1.user.adapter.in.web.mapper.UserWebMapper;
import com.vigilante.retriever.v1.user.adapter.in.web.util.TokenExtractor;
import com.vigilante.retriever.v1.user.application.service.LoginService;
import com.vigilante.retriever.v1.user.application.service.LogoutService;
import com.vigilante.retriever.v1.user.application.service.RegisterUserService;
import com.vigilante.retriever.v1.user.application.service.ReissueTokenService;
import com.vigilante.retriever.v1.user.application.service.WithdrawUserService;
import com.vigilante.retriever.v1.user.domain.dto.command.LoginCommand;
import com.vigilante.retriever.v1.user.domain.dto.command.RegisterUserCommand;
import com.vigilante.retriever.v1.user.domain.vo.LoginResult;
import com.vigilante.retriever.v1.user.domain.vo.TokenResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final RegisterUserService registerUserService;
	private final LoginService loginService;
	private final LogoutService logoutService;
	private final ReissueTokenService reissueTokenService;
	private final WithdrawUserService withdrawUserService;
	private final TokenExtractor tokenExtractor;
	private final CookieProvider cookieProvider;
	private final UserWebMapper userWebMapper;

	@Override
	public ResponseEntity<CommonResponse<Void>> signUp(SignUpRequest signUpRequest) {
		RegisterUserCommand command = userWebMapper.toCommand(signUpRequest);
		registerUserService.signUp(command);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(CommonResponse.created());
	}

	@Override
	public ResponseEntity<CommonResponse<LoginResponse>> login(LoginRequest loginRequest,
		HttpServletResponse httpServletResponse) {
		LoginCommand command = userWebMapper.toCommand(loginRequest);
		LoginResult loginResult = loginService.login(command);

		cookieProvider.setTokenCookies(httpServletResponse,
			loginResult.accessToken(),
			loginResult.refreshToken()
		);

		LoginResponse response = userWebMapper.toResponse(loginResult);
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success(response));
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> logout(String userId, HttpServletResponse httpServletResponse) {
		cookieProvider.deleteTokenCookies(httpServletResponse);
		logoutService.logout(userId);
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.deleted());
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> reissueToken(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
		String refreshToken = tokenExtractor.extractRefreshToken(httpServletRequest);
		TokenResult tokenResult = reissueTokenService.reissueToken(refreshToken);

		cookieProvider.setTokenCookies(httpServletResponse,
			tokenResult.accessToken(),
			tokenResult.refreshToken()
		);

		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.success());
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> withdraw(String loginId) {
		withdrawUserService.withdraw(loginId);
		return ResponseEntity.status(HttpStatus.OK)
			.body(CommonResponse.deleted());
	}
}
