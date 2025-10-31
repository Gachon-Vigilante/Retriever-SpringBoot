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
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
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
	@Mapping(target = "sellsArgots", ignore = true)
	ChannelGraphView toGraphView(ChannelNode document);

	// 채널의 최상단 ChannelGraphView는 promotedByPosts와 sellsArgots를 1 depth만 매핑 (순환 참조 방지)
	@AfterMapping
	default void mapRelationshipsShallow(@MappingTarget ChannelGraphView.ChannelGraphViewBuilder builder,
		ChannelNode node) {
		// promotedByPosts 매핑
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

		// sellsArgots 매핑 (shallow - soldByChannels를 빈 Set으로 설정하여 순환 참조 방지)
		if (node.getSellsArgots() != null && !node.getSellsArgots().isEmpty()) {
			Set<ArgotGraphView> shallowSellsArgots =
				node.getSellsArgots()
					.stream()
					.map(argot -> {
						Set<DrugGraphView> drugViews =
							(argot.getRefersDrugs() != null && !argot.getRefersDrugs().isEmpty()) ?
								argot.getRefersDrugs()
									.stream()
									.map(
										drug -> DrugGraphView.builder()
											.drugBankId(drug.getDrugBankId())
											.name(drug.getName())
											.englishName(drug.getEnglishName())
											.drugType(drug.getDrugType())
											.build())
									.collect(Collectors.toSet()) : Collections.emptySet();

						return ArgotGraphView.builder()
							.name(argot.getName())
							.description(argot.getDescription())
							.refersDrugs(drugViews)
							.soldByChannels(Collections.emptySet()) // 순환 참조 방지
							.build();
					})
					.collect(Collectors.toSet());
			builder.sellsArgots(shallowSellsArgots);
		} else {
			builder.sellsArgots(Collections.emptySet());
		}
	}

	@Override
	List<ChannelNode> getNodeList(List<ChannelGraphView> graphViewList);

	@Override
	List<ChannelGraphView> getGraphViewList(List<ChannelNode> nodeList);
}
