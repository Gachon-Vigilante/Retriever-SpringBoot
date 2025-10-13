package com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.drug.adapter.out.mapper.DrugMongoMapper;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.document.DrugDocument;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.repository.DrugMongoRepository;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugMongoPort;

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

	@Override
	public List<DrugEntity> findByArgot(String argot) {
		List<DrugDocument> drugList = drugMongoRepository.findByArgot(argot);
		return drugPersistenceMapper.getEntityList(drugList);
	}
}
