package com.vigilante.retriever.v1.drug.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.drug.domain.code.DrugErrorCode;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugNeo4jPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class DrugNeo4jQuery {

	private final DrugNeo4jPort drugNeo4jPort;

	public List<DrugGraphView> findAll() {
		return drugNeo4jPort.findAll();
	}

	public DrugGraphView findDrugWithAllRelationships(String drugBankId) {
		return drugNeo4jPort.findDrugWithAllRelationships(drugBankId)
			.orElseThrow(() -> new NotFoundException(DrugErrorCode.DRUG_NOT_FOUND));
	}
}
