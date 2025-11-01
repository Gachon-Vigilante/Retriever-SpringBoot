package com.vigilante.retriever.v1.drug.domain.graphview;

import java.util.Set;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;

import lombok.Builder;

@Builder
public record DrugGraphView(
	String drugBankId,
	String name,
	String englishName,
	String drugType,
	Set<ArgotGraphView> referredByArgots
) {

}
