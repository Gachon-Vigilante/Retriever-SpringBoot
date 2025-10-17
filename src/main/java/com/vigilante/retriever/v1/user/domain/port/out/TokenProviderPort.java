package com.vigilante.retriever.v1.user.domain.port.out;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface TokenProviderPort {

	String issueAccessToken(UsernamePasswordAuthenticationToken authenticationToken);

	String issueRefreshToken(UsernamePasswordAuthenticationToken authenticationToken);

	String validateToken(String token);

	String getUserIdFromJwt(String token);

	com.vigilante.retriever.common.domain.enums.Role getRoleFromJwt(String token);
}

