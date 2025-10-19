package com.vigilante.retriever.v1.drug.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;

public interface GetDrugUseCase {

	DrugEntity getById(String id);

	List<DrugEntity> findAll();

	List<DrugEntity> getByArgot(String argot);
}
