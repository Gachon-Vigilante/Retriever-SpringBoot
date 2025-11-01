package com.vigilante.retriever.v1.drug.adapter.out.mapper;

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
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node.DrugNode;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

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
	@Mapping(target = "referredByArgots", ignore = true)
	DrugGraphView toGraphView(DrugNode document);

	// Drug의 referredByArgots를 1 depth만 매핑하여 순환 참조 방지
	@AfterMapping
	default void mapRelationshipsShallow(
		@MappingTarget DrugGraphView.DrugGraphViewBuilder builder,
		DrugNode node) {
		// referredByArgots 매핑 (shallow - refersDrugs와 soldByChannels를 1 depth만 보여주고 내부는 빈 Set)
		if (node.getReferredByArgots() != null && !node.getReferredByArgots().isEmpty()) {
			Set<ArgotGraphView> shallowReferredByArgots =
				node.getReferredByArgots()
					.stream()
					.map(argot -> {
						// refersDrugs를 1 depth만 매핑
						Set<DrugGraphView> drugViews =
							(argot.getRefersDrugs() != null && !argot.getRefersDrugs().isEmpty()) ?
								argot.getRefersDrugs()
									.stream()
									.map(drug -> DrugGraphView.builder()
										.drugBankId(drug.getDrugBankId())
										.name(drug.getName())
										.englishName(drug.getEnglishName())
										.drugType(drug.getDrugType())
										.referredByArgots(Collections.emptySet()) // 2 depth는 빈 Set
										.build())
									.collect(Collectors.toSet()) : Collections.emptySet();

					// soldByChannels를 1 depth만 매핑
					Set<ChannelGraphView> shallowChannels =
						(argot.getSoldByChannels() != null && !argot.getSoldByChannels().isEmpty()) ?
							argot.getSoldByChannels()
								.stream()
								.map(channel -> {
									// sellsArgots를 1 depth 표시 (내부 관계는 빈 Set)
									Set<ArgotGraphView> channelArgots = 
										(channel.getSellsArgots() != null && !channel.getSellsArgots().isEmpty()) ?
											channel.getSellsArgots()
												.stream()
												.map(a -> ArgotGraphView.builder()
													.name(a.getName())
													.description(a.getDescription())
													.refersDrugs(Collections.emptySet()) // 2 depth는 빈 Set
													.soldByChannels(Collections.emptySet()) // 2 depth는 빈 Set
													.build())
												.collect(Collectors.toSet()) : Collections.emptySet();
									
									// promotedByPosts를 1 depth 표시 (내부 관계는 빈 Set)
									Set<com.vigilante.retriever.v1.post.domain.graphview.PostGraphView> channelPosts =
										(channel.getPromotedByPosts() != null && !channel.getPromotedByPosts().isEmpty()) ?
											channel.getPromotedByPosts()
												.stream()
												.map(post -> com.vigilante.retriever.v1.post.domain.graphview.PostGraphView.builder()
													.postId(post.getPostId())
													.title(post.getTitle())
													.link(post.getLink())
													.domain(post.getDomain())
													.content(post.getContent())
													.cluster(post.getCluster())
													.discoveredAt(post.getDiscoveredAt())
													.updatedAt(post.getUpdatedAt())
													.isDeleted(post.isDeleted())
													.promotesChannels(Collections.emptySet()) // 2 depth는 빈 Set
													.similarPosts(Collections.emptySet()) // 2 depth는 빈 Set
													.build())
												.collect(Collectors.toSet()) : Collections.emptySet();
									
									return ChannelGraphView.builder()
										.channelId(channel.getChannelId())
										.title(channel.getTitle())
										.username(channel.getUsername())
										.status(channel.getStatus())
										.sellsArgots(channelArgots) // 1 depth 표시
										.promotedByPosts(channelPosts) // 1 depth 표시
										.build();
								})
								.collect(Collectors.toSet()) : Collections.emptySet();

						return ArgotGraphView.builder()
							.name(argot.getName())
							.description(argot.getDescription())
							.refersDrugs(drugViews) // 1 depth 표시
							.soldByChannels(shallowChannels) // 1 depth 표시
							.build();
					})
					.collect(Collectors.toSet());
			builder.referredByArgots(shallowReferredByArgots);
		} else {
			builder.referredByArgots(Collections.emptySet());
		}
	}

	@Override
	List<DrugNode> getNodeList(List<DrugGraphView> graphViewList);

	@Override
	List<DrugGraphView> getGraphViewList(List<DrugNode> nodeList);
}
