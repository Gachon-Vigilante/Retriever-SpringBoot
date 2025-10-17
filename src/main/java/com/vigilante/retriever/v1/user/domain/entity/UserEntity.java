package com.vigilante.retriever.v1.user.domain.entity;

import java.time.LocalDateTime;

import com.vigilante.retriever.common.domain.enums.Role;

import lombok.Builder;

@Builder
public record UserEntity(
	String id,
	String loginId,
	String password,
	String name,
	Role role,

	// Mongo Auditing fields
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	public static UserEntity create(String loginId, String encodedPassword, String name, Role role) {
		return UserEntity.builder()
			.loginId(loginId)
			.password(encodedPassword)
			.name(name)
			.role(role)
			.build();
	}

	public UserEntity updateRole(Role newRole) {
		return UserEntity.builder()
			.id(this.id)
			.loginId(this.loginId)
			.password(this.password)
			.name(this.name)
			.role(newRole)
			.createdAt(this.createdAt)
			.updatedAt(LocalDateTime.now())
			.build();
	}
}
