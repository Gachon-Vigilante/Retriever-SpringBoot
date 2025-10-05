package com.vigilante.retriever.v1.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

	ROOT("ROLE_ROOT"),
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private final String roleName;
}
