package com.vigilante.retriever.v1.drug.adapter.in.web.dto.response;

import java.util.Set;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotTraceResponse;

import lombok.Builder;

@Builder
public record DrugTraceResponse(
	String drugBankId,
	String name,
	String englishName,
	String drugType,
	Set<ArgotTraceResponse> referredByArgots
) {
}
