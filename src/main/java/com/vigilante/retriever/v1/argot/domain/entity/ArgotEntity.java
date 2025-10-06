package com.vigilante.retriever.v1.argot.domain.entity;

import lombok.Builder;

@Builder
public record ArgotEntity(
	String _id,
	String drugId,
	String name,
	String description
) {
}
