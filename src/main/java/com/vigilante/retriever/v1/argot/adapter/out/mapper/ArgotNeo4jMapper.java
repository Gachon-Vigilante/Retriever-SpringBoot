package com.vigilante.retriever.v1.argot.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.node.ArgotNode;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ArgotNeo4jMapper extends GenericNeo4jMapper<ArgotNode, ArgotGraphView> {

	@Override
	ArgotNode toNode(ArgotGraphView graphView);

	@Override
	ArgotGraphView toGraphView(ArgotNode document);

	@Override
	List<ArgotNode> getNodeList(List<ArgotGraphView> graphViewList);

	@Override
	List<ArgotGraphView> getGraphViewList(List<ArgotNode> nodeList);
}
