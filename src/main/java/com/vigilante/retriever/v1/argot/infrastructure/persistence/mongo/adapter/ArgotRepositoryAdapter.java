package com.vigilante.retriever.v1.argot.infrastructure.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;
import com.vigilante.retriever.v1.argot.domain.port.out.ArgotMongoPort;
import com.vigilante.retriever.v1.argot.infrastructure.mapper.ArgotMongoMapper;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.mongo.document.ArgotDocument;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.mongo.repository.ArgotMongoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArgotRepositoryAdapter implements ArgotMongoPort {

	private final ArgotMongoRepository argotMongoRepository;
	private final ArgotMongoMapper argotMongoMapper;

	@Override
	public List<ArgotEntity> findAll() {
		List<ArgotDocument> allArgot = argotMongoRepository.findAll();
		return argotMongoMapper.getEntityList(allArgot);
	}

	@Override
	public Optional<ArgotEntity> findById(String id) {
		return argotMongoRepository.findById(id).map(argotMongoMapper::toEntity);
	}

	@Override
	public List<ArgotEntity> findByArgot(String argot) {
		List<ArgotDocument> argotList = argotMongoRepository.findByArgot(argot);
		return argotMongoMapper.getEntityList(argotList);
	}
}
