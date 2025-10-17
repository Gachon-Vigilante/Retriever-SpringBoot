package com.vigilante.retriever.v1.user.adapter.in.web.dto.response;

public record LoginResponse(
	String name,
	String role
) {
	public static LoginResponse of(String name, String role) {
		return new LoginResponse(name, role);
	}
}
