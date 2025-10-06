package com.vigilante.retriever.v1.drug.infrastructure.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugMongoPort;
import com.vigilante.retriever.v1.drug.infrastructure.mapper.DrugMongoMapper;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.mongo.document.DrugDocument;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.mongo.repository.DrugMongoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DrugRepositoryAdapter implements DrugMongoPort {

	private final DrugMongoRepository drugMongoRepository;
	private final DrugMongoMapper drugPersistenceMapper;

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
