package com.vigilante.retriever.v1.drug.domain.graphview;

import java.util.Set;

import lombok.Builder;

@Builder
public record ArgotGraphView(
	Long identity,
	String name,
	String description,
	Set<DrugGraphView> refersDrugs
) {
}
