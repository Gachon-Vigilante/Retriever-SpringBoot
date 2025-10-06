package com.vigilante.retriever.v1.drug.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.exception.DrugNotFoundException;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class DrugQuery {

	private final DrugPersistencePort drugPersistencePort;

	public List<DrugEntity> findAll() {
		return drugPersistencePort.findAll();
	}

	public DrugEntity getById(String id) {
		return drugPersistencePort.findById(id).orElseThrow(DrugNotFoundException::new);
	}
}
