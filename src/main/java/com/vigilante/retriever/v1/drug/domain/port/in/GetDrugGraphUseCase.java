package com.vigilante.retriever.v1.drug.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

public interface GetDrugGraphUseCase {
	List<DrugGraphView> findAll();

	DrugGraphView findDrugWithAllRelationships(String drugBankId);
}
