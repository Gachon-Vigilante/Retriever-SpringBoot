package com.vigilante.retriever.v1.argot.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.argot.domain.port.out.ArgotNeo4jPort;

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
