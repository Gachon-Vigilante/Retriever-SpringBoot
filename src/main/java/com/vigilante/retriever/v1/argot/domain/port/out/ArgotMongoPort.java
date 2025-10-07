package com.vigilante.retriever.v1.argot.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;

public interface ArgotMongoPort {

	List<ArgotEntity> findAll();

	Optional<ArgotEntity> findById(String id);

	List<ArgotEntity> findByArgot(String argot);
}
