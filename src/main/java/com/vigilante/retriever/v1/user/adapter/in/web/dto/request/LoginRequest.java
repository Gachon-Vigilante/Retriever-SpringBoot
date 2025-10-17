package com.vigilante.retriever.v1.user.adapter.in.web.dto.request;

public record LoginRequest(
	String loginId,
	String password
) {
}
