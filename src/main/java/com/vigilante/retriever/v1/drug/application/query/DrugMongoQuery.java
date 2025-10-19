package com.vigilante.retriever.v1.drug.application.query;

import static com.vigilante.retriever.v1.drug.domain.code.DrugErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
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
		return drugMongoPort.findById(id).orElseThrow(() -> new NotFoundException(DRUG_NOT_FOUND));
	}

	public List<DrugEntity> getByArgot(String argot) {
		return drugMongoPort.findByArgot(argot);
	}
}
