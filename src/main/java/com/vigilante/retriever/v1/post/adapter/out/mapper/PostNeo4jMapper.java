package com.vigilante.retriever.v1.post.adapter.out.mapper;

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
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node.PostNode;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

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
	@Mapping(target = "promotesChannels", ignore = true)
	@Mapping(target = "similarPosts", ignore = true)
	PostGraphView toGraphView(PostNode document);

	// 최상단 PostGraphView는 1 depth만 매핑 (중첩된 관계는 빈 Set 처리)
	@AfterMapping
	default void mapRelationshipsShallow(@MappingTarget PostGraphView.PostGraphViewBuilder builder, PostNode node) {
		// promotesChannels 매핑 (1 depth만)
		if (node.getPromotesChannels() != null && !node.getPromotesChannels().isEmpty()) {
			Set<PostGraphView.Promote> shallowPromotes = node.getPromotesChannels().stream()
				.map(promote -> PostGraphView.Promote.builder()
					.id(promote.getId())
					.channel(promote.getChannel() != null
						? ChannelGraphView.builder()
						.channelId(promote.getChannel().getChannelId())
						.title(promote.getChannel().getTitle())
						.username(promote.getChannel().getUsername())
						.status(promote.getChannel().getStatus())
						.sellsArgots(Collections.emptySet())
						.build()
						: null)
					.build())
				.collect(Collectors.toSet());
			builder.promotesChannels(shallowPromotes);
		} else {
			builder.promotesChannels(Collections.emptySet());
		}

		// similarPosts 매핑 (1 depth만)
		if (node.getSimilarPosts() != null && !node.getSimilarPosts().isEmpty()) {
			Set<PostGraphView> shallowSimilarPosts = node.getSimilarPosts().stream()
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
				.collect(Collectors.toSet());
			builder.similarPosts(shallowSimilarPosts);
		} else {
			builder.similarPosts(Collections.emptySet());
		}
	}

	@Override
	List<PostNode> getNodeList(List<PostGraphView> graphViewList);

	@Override
	List<PostGraphView> getGraphViewList(List<PostNode> nodeList);
}
