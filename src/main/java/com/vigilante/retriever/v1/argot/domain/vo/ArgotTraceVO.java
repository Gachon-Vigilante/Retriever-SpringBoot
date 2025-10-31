package com.vigilante.retriever.v1.argot.domain.vo;

import java.util.Set;
import java.util.stream.Collectors;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.post.domain.vo.PostTraceVO;

import lombok.Builder;

@Builder
public record ArgotTraceVO(
	String name,
	String description,
	Set<SoldByChannel> soldByChannels,
	Set<DrugGraphView> refersToDrugs
) {
	public static ArgotTraceVO create(ArgotGraphView argotGraphView) {
		return ArgotTraceVO.builder()
			.name(argotGraphView.name())
			.description(argotGraphView.description())
			.soldByChannels(argotGraphView.soldByChannels().stream()
				.map(channelGraphView -> SoldByChannel.builder()
					.id(channelGraphView.channelId())
					.title(channelGraphView.title())
					.username(channelGraphView.username())
					.status(channelGraphView.status())
					.promotingPosts(PostTraceVO.mapPromotingPosts(channelGraphView.promotedByPosts()))
					.build())
				.collect(Collectors.toSet()))
			.refersToDrugs(argotGraphView.refersDrugs())
			.build();
	}

	@Builder
	public record SoldByChannel(
		Long id,
		String title,
		String username,
		String status,
		Set<PostTraceVO> promotingPosts
	) {
	}
}
