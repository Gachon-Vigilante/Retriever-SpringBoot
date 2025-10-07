package com.vigilante.retriever.v1.drug.domain.entity;

import lombok.Builder;

@Builder
public record DrugEntity(
	String _id,
	String drugName,
	String drugType,
	String drugEnName
) {
}
