package com.vigilante.retriever.v1.argot.adapter.in.web.dto.response;

import java.util.Set;

import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;

import lombok.Builder;

@Builder
public record ArgotGraphInfoResponse(
	String name,
	String description,
	Set<DrugGraphInfoResponse> refersDrugs
) {
}
