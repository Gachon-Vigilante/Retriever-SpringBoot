package com.vigilante.retriever.v1.channel.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.neo4j.node.ChannelNode;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelNeo4jMapper extends GenericNeo4jMapper<ChannelNode, ChannelGraphView> {

	@Override
	ChannelNode toNode(ChannelGraphView graphView);

	@Override
	ChannelGraphView toGraphView(ChannelNode document);

	@Override
	List<ChannelNode> getNodeList(List<ChannelGraphView> graphViewList);

	@Override
	List<ChannelGraphView> getGraphViewList(List<ChannelNode> nodeList);
}
