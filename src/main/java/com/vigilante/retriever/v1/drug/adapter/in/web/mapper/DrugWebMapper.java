package com.vigilante.retriever.v1.drug.adapter.in.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugInfoResponse;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

@Component
public class DrugWebMapper {

	public DrugInfoResponse toResponse(DrugEntity entity) {
		return DrugInfoResponse.builder()
			.id(entity.id())
			.drugBankId(entity.drugBankId())
			.name(entity.name())
			.drugType(entity.drugType())
			.englishName(entity.englishName())
			.argots(mapArgots(entity.argots()))
			.build();
	}

	private List<DrugInfoResponse.Argot> mapArgots(List<DrugEntity.Argot> argots) {
		if (argots == null) {
			return null;
		}

		return argots.stream()
			.map(argot -> DrugInfoResponse.Argot.builder()
				.name(argot.name())
				.description(argot.description())
				.build())
			.toList();
	}

	public List<DrugInfoResponse> toResponseList(List<DrugEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}

	public DrugGraphInfoResponse toGraphResponse(DrugGraphView graphView) {
		return DrugGraphInfoResponse.builder()
			.drugBankId(graphView.drugBankId())
			.name(graphView.name())
			.englishName(graphView.englishName())
			.drugType(graphView.drugType())
			.build();
	}

	public List<DrugGraphInfoResponse> toGraphResponseList(List<DrugGraphView> graphViews) {
		return graphViews.stream()
			.map(this::toGraphResponse)
			.toList();
	}
}
