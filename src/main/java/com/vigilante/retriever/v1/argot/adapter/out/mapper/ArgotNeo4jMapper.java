package com.vigilante.retriever.v1.argot.adapter.out.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
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
	@org.mapstruct.Mapping(target = "soldByChannels", ignore = true)
	ArgotGraphView toGraphView(ArgotNode document);

	// Argot의 soldByChannels를 1 depth만 매핑하여 순환 참조 방지
	@AfterMapping
	default void mapRelationshipsShallow(
		@MappingTarget com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView.ArgotGraphViewBuilder builder,
		ArgotNode node) {
		// soldByChannels 매핑 (shallow - sellsArgots를 빈 Set으로 설정하고, promotedByPosts는 1 depth만 매핑)
		if (node.getSoldByChannels() != null && !node.getSoldByChannels().isEmpty()) {
			Set<com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView> shallowSoldByChannels =
				node.getSoldByChannels()
					.stream()
					.map(channel -> {
						// promotedByPosts를 1 depth만 매핑 (내부 관계는 빈 Set)
						Set<com.vigilante.retriever.v1.post.domain.graphview.PostGraphView> shallowPosts =
							(channel.getPromotedByPosts() != null && !channel.getPromotedByPosts().isEmpty()) ?
								channel.getPromotedByPosts()
									.stream()
									.map(
										post -> com.vigilante.retriever.v1.post.domain.graphview.PostGraphView.builder()
											.postId(post.getPostId())
											.title(post.getTitle())
											.link(post.getLink())
											.domain(post.getDomain())
											.content(post.getContent())
											.cluster(post.getCluster())
											.discoveredAt(post.getDiscoveredAt())
											.updatedAt(post.getUpdatedAt())
											.isDeleted(post.isDeleted())
											.promotesChannels(Collections.emptySet()) // 순환 참조 방지
											.similarPosts(Collections.emptySet()) // 순환 참조 방지
											.build())
									.collect(Collectors.toSet()) : Collections.emptySet();

						return com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView.builder()
							.channelId(channel.getChannelId())
							.title(channel.getTitle())
							.username(channel.getUsername())
							.status(channel.getStatus())
							.sellsArgots(Collections.emptySet()) // 순환 참조 방지
							.promotedByPosts(shallowPosts) // 1 depth만 포함
							.build();
					})
					.collect(Collectors.toSet());
			builder.soldByChannels(shallowSoldByChannels);
		} else {
			builder.soldByChannels(Collections.emptySet());
		}
	}

	@Override
	List<ArgotNode> getNodeList(List<ArgotGraphView> graphViewList);

	@Override
	List<ArgotGraphView> getGraphViewList(List<ArgotNode> nodeList);
}
