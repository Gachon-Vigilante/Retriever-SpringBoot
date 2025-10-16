package com.vigilante.retriever.common.domain.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vigilante.retriever.common.domain.exception.RoleNotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

	ROOT("ROLE_ROOT"),
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private final String roleName;

	public static Role fromRoleName(String roleName) {
		for (Role role : Role.values()) {
			if (role.roleName.equals(roleName)) {
				return role;
			}
		}
		throw new RoleNotFoundException();
	}

	public GrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority(this.roleName);
	}
}

