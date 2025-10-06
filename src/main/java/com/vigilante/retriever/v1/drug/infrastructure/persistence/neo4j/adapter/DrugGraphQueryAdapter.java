package com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.drug.domain.port.out.DrugNeo4jPort;
import com.vigilante.retriever.v1.drug.infrastructure.mapper.DrugNeo4jMapper;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.node.DrugNode;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.repository.DrugNeo4jRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DrugGraphQueryAdapter implements DrugNeo4jPort {

	private final DrugNeo4jRepository drugNeo4jRepository;
	private final DrugNeo4jMapper drugGraphMapper;

	@Override
	public List<DrugGraphView> findAll() {
		List<DrugNode> allDrug = drugNeo4jRepository.findAll();
		return drugGraphMapper.getGraphViewList(allDrug);
	}
}
