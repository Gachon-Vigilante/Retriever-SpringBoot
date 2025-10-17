package com.vigilante.retriever.v1.user.application.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.common.domain.exception.UnauthorizedException;
import com.vigilante.retriever.v1.user.application.command.RefreshTokenRedisCommand;
import com.vigilante.retriever.v1.user.application.query.UserMongoQuery;
import com.vigilante.retriever.v1.user.domain.code.UserErrorCode;
import com.vigilante.retriever.v1.user.domain.dto.command.LoginCommand;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.in.LoginUseCase;
import com.vigilante.retriever.v1.user.domain.port.out.AuthenticationPort;
import com.vigilante.retriever.v1.user.domain.port.out.PasswordEncoderPort;
import com.vigilante.retriever.v1.user.domain.port.out.TokenProviderPort;
import com.vigilante.retriever.v1.user.domain.vo.LoginResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

	private final UserMongoQuery userMongoQuery;
	private final RefreshTokenRedisCommand refreshTokenRedisCommand;
	private final PasswordEncoderPort passwordEncoderPort;
	private final TokenProviderPort tokenProviderPort;
	private final AuthenticationPort authenticationPort;

	@Override
	public LoginResult login(LoginCommand command) {
		UserEntity user = findUserWithAuthenticate(command.loginId(), command.password());

		Role role = user.role();
		String userId = user.id();
		String name = user.name();

		Collection<GrantedAuthority> authorities = List.of(role.toGrantedAuthority());

		UsernamePasswordAuthenticationToken authenticationToken = authenticationPort.createAuthentication(
			userId, role, authorities);
		String refreshToken = issueAndSaveRefreshToken(userId, authenticationToken);
		String accessToken = tokenProviderPort.issueAccessToken(authenticationToken);

		log.info("로그인에 성공하였습니다. authorities: {}, accessToken: {}, refreshToken: {}", authorities, accessToken,
			refreshToken);

		return LoginResult.of(accessToken, refreshToken, name, role.getRoleName());
	}

	private UserEntity findUserWithAuthenticate(String loginId, String password) {
		UserEntity user = userMongoQuery.findByLoginId(loginId);

		if (!passwordEncoderPort.matches(password, user.password())) {
			throw new UnauthorizedException(UserErrorCode.PASSWORD_MISMATCH);
		}

		return user;
	}

	private String issueAndSaveRefreshToken(String userId, UsernamePasswordAuthenticationToken authenticationToken) {
		String refreshToken = tokenProviderPort.issueRefreshToken(authenticationToken);
		log.info("새로운 refresh token이 발급되었습니다. userId: {}", userId);

		RefreshTokenEntity tokenEntity = RefreshTokenEntity.builder()
			.userId(userId)
			.tokenValue(refreshToken)
			.build();

		refreshTokenRedisCommand.save(tokenEntity);
		return refreshToken;
	}
}

