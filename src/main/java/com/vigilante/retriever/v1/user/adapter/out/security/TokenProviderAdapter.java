package com.vigilante.retriever.v1.user.adapter.out.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.infrastructure.auth.jwt.provider.JwtTokenProvider;
import com.vigilante.retriever.v1.user.domain.port.out.TokenProviderPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenProviderAdapter implements TokenProviderPort {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public String issueAccessToken(UsernamePasswordAuthenticationToken authenticationToken) {
		return jwtTokenProvider.issueAccessToken(authenticationToken);
	}

	@Override
	public String issueRefreshToken(UsernamePasswordAuthenticationToken authenticationToken) {
		return jwtTokenProvider.issueRefreshToken(authenticationToken);
	}

	@Override
	public String validateToken(String token) {
		return jwtTokenProvider.validateToken(token).name();
	}

	@Override
	public String getUserIdFromJwt(String token) {
		return jwtTokenProvider.getUserIdFromJwt(token);
	}

	@Override
	public Role getRoleFromJwt(String token) {
		return jwtTokenProvider.getRoleFromJwt(token);
	}
}

