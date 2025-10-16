package com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.drug.adapter.out.mapper.ArgotNeo4jMapper;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node.ArgotNode;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.repository.ArgotNeo4jRepository;
import com.vigilante.retriever.v1.drug.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.drug.domain.port.out.ArgotNeo4jPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArgotNeo4jQueryAdapter implements ArgotNeo4jPort {

	private final ArgotNeo4jRepository argotNeo4jRepository;
	private final ArgotNeo4jMapper argotGraphMapper;

	@Override
	public List<ArgotGraphView> findAll() {
		List<ArgotNode> allArgot = argotNeo4jRepository.findAll();
		return argotGraphMapper.getGraphViewList(allArgot);
	}

	@Override
	public List<ArgotGraphView> findAllWithRefersTo() {
		List<ArgotNode> argotNodes = argotNeo4jRepository.findAllWithRefersTo();
		return argotGraphMapper.getGraphViewList(argotNodes);
	}
}
