package com.vigilante.retriever.v1.drug.domain.graphview;

import java.util.Set;

import lombok.Builder;

@Builder
public record ArgotGraphView(
	String name,
	String description,
	Set<DrugGraphView> refersDrugs
) {
}
