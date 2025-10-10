package com.vigilante.retriever.infrastructure.auth.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityEndpointRegistry {

	// 인증 없이 접근 허용
	public String[] whitelist() {
		return new String[] {
			"/error",
			"/auth/login",
			"/auth/reissue",
			"/swagger-ui/**",
			"/v3/api-docs/**"
		};
	}

	// ADMIN 이상 권한 필요
	public String[] adminOrRootOnly() {
		return new String[] {
			"/auth/signup",
			"/user"
		};
	}

	// ROOT 이상 권한 필요
	public String[] rootOnly() {
		return new String[] {
			"/user/grant-role"
		};
	}
}

