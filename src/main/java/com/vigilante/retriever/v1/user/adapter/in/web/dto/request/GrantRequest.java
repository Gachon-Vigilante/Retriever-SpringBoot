package com.vigilante.retriever.v1.user.adapter.in.web.dto.request;

import com.vigilante.retriever.common.domain.enums.Role;

public record GrantRequest(
	String loginId,
	Role role
) {
}
