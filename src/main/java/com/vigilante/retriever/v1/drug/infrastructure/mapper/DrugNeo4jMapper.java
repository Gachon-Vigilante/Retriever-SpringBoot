package com.vigilante.retriever.v1.drug.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.node.DrugNode;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface DrugNeo4jMapper extends GenericNeo4jMapper<DrugNode, DrugGraphView> {

	@Override
	DrugNode toNode(DrugGraphView graphView);

	@Override
	DrugGraphView toGraphView(DrugNode document);

	@Override
	List<DrugNode> getNodeList(List<DrugGraphView> graphViewList);

	@Override
	List<DrugGraphView> getGraphViewList(List<DrugNode> dodumentList);
}
