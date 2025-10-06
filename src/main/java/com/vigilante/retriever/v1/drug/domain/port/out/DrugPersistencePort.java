package com.vigilante.retriever.v1.drug.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;

public interface DrugPersistencePort {

	List<DrugEntity> findAll();

	Optional<DrugEntity> findById(String id);
}
