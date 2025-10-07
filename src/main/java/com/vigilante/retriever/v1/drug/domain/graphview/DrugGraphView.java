package com.vigilante.retriever.v1.drug.domain.graphview;

import lombok.Builder;

@Builder
public record DrugGraphView(
	String id,
	String name,
	String englishName,
	String type
) {
}
