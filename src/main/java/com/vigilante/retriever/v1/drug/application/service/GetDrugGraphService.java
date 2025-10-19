package com.vigilante.retriever.v1.drug.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.drug.application.query.DrugNeo4jQuery;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.drug.domain.port.in.GetDrugGraphUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetDrugGraphService implements GetDrugGraphUseCase {

	private final DrugNeo4jQuery drugNeo4jQuery;

	@Override
	public List<DrugGraphView> findAll() {
		return drugNeo4jQuery.findAll();
	}
}
