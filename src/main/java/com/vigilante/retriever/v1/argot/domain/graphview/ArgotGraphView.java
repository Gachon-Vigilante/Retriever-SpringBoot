package com.vigilante.retriever.v1.argot.domain.graphview;

import java.util.Set;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

import lombok.Builder;

@Builder
public record ArgotGraphView(
	String name,
	String description,
	Set<DrugGraphView> refersDrugs
) {
}
