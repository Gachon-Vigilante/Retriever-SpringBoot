package com.vigilante.retriever.v1.drug.domain.graphview;

import lombok.Builder;

@Builder
public record DrugGraphView(
	String drugId,
	String name,
	String englishName,
	String drugType
) {
}
