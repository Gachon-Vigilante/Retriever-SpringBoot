package com.vigilante.retriever.v1.drug.adapter.in.web.dto.response;

import lombok.Builder;

@Builder
public record DrugGraphInfoResponse(
	String drugBankId,
	String name,
	String englishName,
	String drugType
) {
}
