package com.vigilante.retriever.v1.drug.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.exception.DrugNotFoundException;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class DrugMongoQuery {

	private final DrugMongoPort drugMongoPort;

	public List<DrugEntity> findAll() {
		return drugMongoPort.findAll();
	}

	public DrugEntity getById(String id) {
		return drugMongoPort.findById(id).orElseThrow(DrugNotFoundException::new);
	}
}
