package com.vigilante.retriever.v1.post.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.infrastructure.persistence.neo4j.node.PostNode;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PostNeo4jMapper extends GenericNeo4jMapper<PostNode, PostGraphView> {

	@Override
	PostNode toNode(PostGraphView graphView);

	@Override
	PostGraphView toGraphView(PostNode document);

	@Override
	List<PostNode> getNodeList(List<PostGraphView> graphViewList);

	@Override
	List<PostGraphView> getGraphViewList(List<PostNode> nodeList);
}
