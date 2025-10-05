package com.vigilante.retriever.v1.user.domain.entity;

import java.time.LocalDateTime;

import com.vigilante.retriever.v1.user.domain.enums.Role;

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
}
