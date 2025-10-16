package com.vigilante.retriever.v1.drug.domain.entity;

import java.util.List;

import lombok.Builder;

@Builder
public record DrugEntity(
	String id,
	String drugBankId,
	String name,
	String drugType,
	String EnglishName,
	List<Argot> argots
) {
	@Builder
	public record Argot(
		String name,
		String description
	) {
	}
}
