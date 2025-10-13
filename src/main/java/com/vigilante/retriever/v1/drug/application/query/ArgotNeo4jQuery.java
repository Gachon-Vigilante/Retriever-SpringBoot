package com.vigilante.retriever.v1.drug.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.drug.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.drug.domain.port.out.ArgotNeo4jPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ArgotNeo4jQuery {

	private final ArgotNeo4jPort argotNeo4jPort;

	public List<ArgotGraphView> findAll() {
		return argotNeo4jPort.findAll();
	}

	public List<ArgotGraphView> findAllWithRefersTo() {
		return argotNeo4jPort.findAllWithRefersTo();
	}
}
