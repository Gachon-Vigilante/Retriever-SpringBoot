package com.vigilante.retriever.v1.argot.adapter.in.web.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

@Component
public class ArgotWebMapper {

	public ArgotGraphInfoResponse toGraphResponse(ArgotGraphView graphView) {
		return ArgotGraphInfoResponse.builder()
			.name(graphView.name())
			.description(graphView.description())
			.refersDrugs(mapRefersDrugs(graphView.refersDrugs()))
			.build();
	}

	private Set<DrugGraphInfoResponse> mapRefersDrugs(Set<DrugGraphView> drugs) {
		return drugs.stream()
			.map(drug -> DrugGraphInfoResponse.builder()
				.drugId(drug.drugId())
				.name(drug.name())
				.englishName(drug.englishName())
				.drugType(drug.drugType())
				.build())
			.collect(Collectors.toSet());
	}

	public List<ArgotGraphInfoResponse> toGraphResponseList(List<ArgotGraphView> graphViews) {
		return graphViews.stream()
			.map(this::toGraphResponse)
			.toList();
	}
}
