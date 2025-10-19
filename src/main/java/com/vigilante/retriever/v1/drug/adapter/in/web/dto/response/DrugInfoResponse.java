package com.vigilante.retriever.v1.drug.adapter.in.web.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record DrugInfoResponse(
	String id,
	String drugBankId,
	String name,
	String drugType,
	String englishName,
	List<Argot> argots
) {
	@Builder
	public record Argot(
		String name,
		String description
	) {
	}
}
