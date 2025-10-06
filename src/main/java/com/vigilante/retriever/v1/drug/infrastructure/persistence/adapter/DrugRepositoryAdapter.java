package com.vigilante.retriever.v1.drug.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugPersistencePort;
import com.vigilante.retriever.v1.drug.infrastructure.mapper.DrugPersistenceMapper;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.document.DrugDocument;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.repository.DrugMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DrugRepositoryAdapter implements DrugPersistencePort {

	private final DrugMongoRepository drugMongoRepository;
	private final DrugPersistenceMapper drugPersistenceMapper;

	@Override
	public List<DrugEntity> findAll() {
		List<DrugDocument> allDrugs = drugMongoRepository.findAll();
		return drugPersistenceMapper.getEntityList(allDrugs);
	}

	@Override
	public Optional<DrugEntity> findById(String id) {
		return drugMongoRepository.findById(id).map(drugPersistenceMapper::toEntity);
	}
}
