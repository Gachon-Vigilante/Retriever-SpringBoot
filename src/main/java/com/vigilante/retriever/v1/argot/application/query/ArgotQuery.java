package com.vigilante.retriever.v1.argot.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;
import com.vigilante.retriever.v1.argot.domain.exception.ArgotNotFoundException;
import com.vigilante.retriever.v1.argot.domain.port.out.ArgotPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ArgotQuery {

	private final ArgotPersistencePort argotPersistencePort;

	List<ArgotEntity> findAll() {
		return argotPersistencePort.findAll();
	}

	ArgotEntity getById(String id) {
		return argotPersistencePort.findById(id).orElseThrow(ArgotNotFoundException::new);
	}

	List<ArgotEntity> getByArgot(String argot) {
		return argotPersistencePort.findByArgot(argot);
	}
}
