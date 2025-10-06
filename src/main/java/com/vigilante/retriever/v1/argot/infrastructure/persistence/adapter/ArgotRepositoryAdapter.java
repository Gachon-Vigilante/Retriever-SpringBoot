package com.vigilante.retriever.v1.argot.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;
import com.vigilante.retriever.v1.argot.domain.port.out.ArgotPersistencePort;
import com.vigilante.retriever.v1.argot.infrastructure.mapper.ArgotPersistenceMapper;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.document.ArgotDocument;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.repository.ArgotMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArgotRepositoryAdapter implements ArgotPersistencePort {

	private final ArgotMongoRepository argotMongoRepository;
	private final ArgotPersistenceMapper argotPersistenceMapper;

	@Override
	public List<ArgotEntity> findAll() {
		List<ArgotDocument> allArgot = argotMongoRepository.findAll();
		return argotPersistenceMapper.getEntityList(allArgot);
	}

	@Override
	public Optional<ArgotEntity> findById(String id) {
		return argotMongoRepository.findById(id).map(argotPersistenceMapper::toEntity);
	}

	@Override
	public List<ArgotEntity> findByArgot(String argot) {
		List<ArgotDocument> argotList = argotMongoRepository.findByArgot(argot);
		return argotPersistenceMapper.getEntityList(argotList);
	}
}
