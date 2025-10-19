package com.vigilante.retriever.v1.drug.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.drug.application.query.DrugMongoQuery;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.port.in.GetDrugUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetDrugService implements GetDrugUseCase {

	private final DrugMongoQuery drugMongoQuery;

	@Override
	public DrugEntity getById(String id) {
		return drugMongoQuery.getById(id);
	}

	@Override
	public List<DrugEntity> findAll() {
		return drugMongoQuery.findAll();
	}

	@Override
	public List<DrugEntity> getByArgot(String argot) {
		return drugMongoQuery.getByArgot(argot);
	}
}
