package com.vigilante.retriever.v1.user.adapter.in.web.dto.request;

public record SignUpRequest(
	String loginId,
	String password,
	String name
) {
}
