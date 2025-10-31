package com.vigilante.retriever.v1.argot.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.argot.application.query.ArgotNeo4jQuery;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.argot.domain.port.in.GetArgotGraphUseCase;
import com.vigilante.retriever.v1.argot.domain.vo.ArgotTraceVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetArgotGraphService implements GetArgotGraphUseCase {

	private final ArgotNeo4jQuery argotNeo4jQuery;

	@Override
	public List<ArgotGraphView> findAll() {
		return argotNeo4jQuery.findAll();
	}

	@Override
	public List<ArgotGraphView> findAllWithRefersTo() {
		return argotNeo4jQuery.findAllWithRefersTo();
	}

	@Override
	public ArgotTraceVO findArgotWithAllRelations(String name) {
		ArgotGraphView argot = argotNeo4jQuery.findArgotWithAllRelations(name);
		return ArgotTraceVO.create(argot);
	}
}
