package com.vigilante.retriever.v1.user.adapter.in.web.controller;

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
import com.vigilante.retriever.v1.user.domain.dto.command.LoginCommand;
import com.vigilante.retriever.v1.user.domain.dto.command.RegisterUserCommand;
import com.vigilante.retriever.v1.user.domain.port.in.LoginUseCase;
import com.vigilante.retriever.v1.user.domain.port.in.LogoutUseCase;
import com.vigilante.retriever.v1.user.domain.port.in.RegisterUserUseCase;
import com.vigilante.retriever.v1.user.domain.port.in.ReissueTokenUseCase;
import com.vigilante.retriever.v1.user.domain.port.in.WithdrawUserUseCase;
import com.vigilante.retriever.v1.user.domain.vo.LoginResult;
import com.vigilante.retriever.v1.user.domain.vo.TokenResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final RegisterUserUseCase registerUserUseCase;
	private final LoginUseCase loginUseCase;
	private final LogoutUseCase logoutUseCase;
	private final ReissueTokenUseCase reissueTokenUseCase;
	private final WithdrawUserUseCase withdrawUserUseCase;
	private final TokenExtractor tokenExtractor;
	private final CookieProvider cookieProvider;
	private final UserWebMapper userWebMapper;

	@Override
	public ResponseEntity<CommonResponse<Void>> signUp(SignUpRequest signUpRequest) {
		RegisterUserCommand command = userWebMapper.toCommand(signUpRequest);
		registerUserUseCase.signUp(command);
		return CommonResponse.created();
	}

	@Override
	public ResponseEntity<CommonResponse<LoginResponse>> login(LoginRequest loginRequest,
		HttpServletResponse httpServletResponse) {
		LoginCommand command = userWebMapper.toCommand(loginRequest);
		LoginResult loginResult = loginUseCase.login(command);

		cookieProvider.setTokenCookies(httpServletResponse,
			loginResult.accessToken(),
			loginResult.refreshToken()
		);

		LoginResponse response = userWebMapper.toResponse(loginResult);
		return CommonResponse.success(response);
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> logout(String userId, HttpServletResponse httpServletResponse) {
		cookieProvider.deleteTokenCookies(httpServletResponse);
		logoutUseCase.logout(userId);
		return CommonResponse.deleted();
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> reissueToken(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
		String refreshToken = tokenExtractor.extractRefreshToken(httpServletRequest);
		TokenResult tokenResult = reissueTokenUseCase.reissueToken(refreshToken);

		cookieProvider.setTokenCookies(httpServletResponse,
			tokenResult.accessToken(),
			tokenResult.refreshToken()
		);

		return CommonResponse.success();
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> withdraw(String loginId) {
		withdrawUserUseCase.withdraw(loginId);
		return CommonResponse.deleted();
	}
}
