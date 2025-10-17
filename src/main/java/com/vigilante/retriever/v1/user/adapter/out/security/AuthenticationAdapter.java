package com.vigilante.retriever.v1.user.adapter.out.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.infrastructure.auth.security.AdminAuthentication;
import com.vigilante.retriever.infrastructure.auth.security.MemberAuthentication;
import com.vigilante.retriever.v1.user.domain.port.out.AuthenticationPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationAdapter implements AuthenticationPort {

	@Override
	public UsernamePasswordAuthenticationToken createAuthentication(
		String userId,
		Role role,
		Collection<GrantedAuthority> authorities
	) {
		if (role == Role.USER) {
			log.info("Creating MemberAuthentication for userId: {}", userId);
			return new MemberAuthentication(userId, null, authorities);
		} else {
			log.info("Creating AdminAuthentication for userId: {}", userId);
			return new AdminAuthentication(userId, null, authorities);
		}
	}
}

