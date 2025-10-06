package com.vigilante.retriever.v1.argot.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.neo4j.node.ArgotNode;

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
	List<ArgotGraphView> getGraphViewList(List<ArgotNode> dodumentList);
}
