package com.vigilante.retriever.v1.argot.adapter.in.web.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;
import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotTraceResponse;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.argot.domain.vo.ArgotTraceVO;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.mapper.DrugWebMapper;
import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.post.adapter.in.web.mapper.PostWebMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArgotWebMapper {

	private final DrugWebMapper drugWebMapper;
	private final ObjectProvider<PostWebMapper> postWebMapperProvider;

	public ArgotGraphInfoResponse toGraphResponse(ArgotGraphView graphView) {
		return ArgotGraphInfoResponse.builder()
			.name(graphView.name())
			.description(graphView.description())
			.refersDrugs(mapRefersDrugs(graphView.refersDrugs()))
			.build();
	}

	private Set<DrugGraphInfoResponse> mapRefersDrugs(Set<DrugGraphView> drugs) {
		return drugs.stream()
			.map(drugWebMapper::toGraphResponse)
			.collect(Collectors.toSet());
	}

	public List<ArgotGraphInfoResponse> toGraphResponseList(List<ArgotGraphView> graphViews) {
		return graphViews.stream()
			.map(this::toGraphResponse)
			.toList();
	}

	public ArgotTraceResponse toTraceResponse(ArgotTraceVO vo) {
		PostWebMapper postWebMapper = postWebMapperProvider.getObject();
		Set<ArgotTraceResponse.SoldByChannel> soldByChannels = vo.soldByChannels().stream()
			.map(channelVO -> ArgotTraceResponse.SoldByChannel.builder()
				.id(channelVO.id())
				.title(channelVO.title())
				.username(channelVO.username())
				.status(channelVO.status())
				.promotingPosts(postWebMapper.mapSimilarPosts(channelVO.promotingPosts()))
				.build())
			.collect(Collectors.toSet());

		return ArgotTraceResponse.builder()
			.name(vo.name())
			.description(vo.description())
			.soldByChannels(soldByChannels)
			.refersToDrugs(vo.refersToDrugs())
			.build();
	}
}
