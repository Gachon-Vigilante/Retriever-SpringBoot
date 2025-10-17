package com.vigilante.retriever.v1.user.domain.port.out;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.vigilante.retriever.common.domain.enums.Role;

public interface AuthenticationPort {

	UsernamePasswordAuthenticationToken createAuthentication(String userId, Role role,
		Collection<GrantedAuthority> authorities);
}

