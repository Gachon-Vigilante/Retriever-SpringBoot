package com.vigilante.retriever.v1.channel.adapter.out.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericNeo4jMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

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
	@Mapping(target = "promotedByPosts", ignore = true)
	ChannelGraphView toGraphView(ChannelNode document);

	// 채널의 최상단 ChannelGraphView는 promotedByPosts를 1 depth만 매핑
	@AfterMapping
	default void mapRelationshipsShallow(@MappingTarget ChannelGraphView.ChannelGraphViewBuilder builder,
		ChannelNode node) {
		if (node.getPromotedByPosts() != null && !node.getPromotedByPosts().isEmpty()) {
			Set<PostGraphView> shallowPromotedByPosts = node.getPromotedByPosts()
				.stream()
				.map(post -> PostGraphView.builder()
					.postId(post.getPostId())
					.title(post.getTitle())
					.link(post.getLink())
					.domain(post.getDomain())
					.content(post.getContent())
					.cluster(post.getCluster())
					.discoveredAt(post.getDiscoveredAt())
					.updatedAt(post.getUpdatedAt())
					.isDeleted(post.isDeleted())
					.promotesChannels(Collections.emptySet())
					// similarPosts는 1 depth만 매핑: 내부는 빈 Set으로 하여 추가 재귀를 방지
					.similarPosts(post.getSimilarPosts() != null && !post.getSimilarPosts().isEmpty() ?
						post.getSimilarPosts()
							.stream()
							.map(similarPost -> PostGraphView.builder()
								.postId(similarPost.getPostId())
								.title(similarPost.getTitle())
								.link(similarPost.getLink())
								.domain(similarPost.getDomain())
								.content(similarPost.getContent())
								.cluster(similarPost.getCluster())
								.discoveredAt(similarPost.getDiscoveredAt())
								.updatedAt(similarPost.getUpdatedAt())
								.isDeleted(similarPost.isDeleted())
								.promotesChannels(Collections.emptySet())
								.similarPosts(Collections.emptySet())
								.build())
							.collect(Collectors.toSet()) : Collections.emptySet())
					.build())
				.collect(Collectors.toSet());
			builder.promotedByPosts(shallowPromotedByPosts);
		} else {
			builder.promotedByPosts(Collections.emptySet());
		}
	}

	@Override
	List<ChannelNode> getNodeList(List<ChannelGraphView> graphViewList);

	@Override
	List<ChannelGraphView> getGraphViewList(List<ChannelNode> nodeList);
}
