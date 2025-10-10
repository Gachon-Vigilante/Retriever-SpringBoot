package com.vigilante.retriever.v1.argot.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;
import com.vigilante.retriever.v1.argot.domain.exception.ArgotNotFoundException;
import com.vigilante.retriever.v1.argot.domain.port.out.ArgotMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ArgotMongoQuery {

	private final ArgotMongoPort argotMongoPort;

	List<ArgotEntity> findAll() {
		return argotMongoPort.findAll();
	}

	ArgotEntity getById(String id) {
		return argotMongoPort.findById(id).orElseThrow(ArgotNotFoundException::new);
	}

	List<ArgotEntity> getByArgot(String argot) {
		return argotMongoPort.findByArgot(argot);
	}
}
