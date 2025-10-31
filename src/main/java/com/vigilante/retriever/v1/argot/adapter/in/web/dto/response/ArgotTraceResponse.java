package com.vigilante.retriever.v1.argot.adapter.in.web.dto.response;

import java.util.Set;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostTraceResponse;

import lombok.Builder;

@Builder
public record ArgotTraceResponse(
	String name,
	String description,
	Set<SoldByChannel> soldByChannels,
	Set<DrugGraphView> refersToDrugs
) {
	@Builder
	public record SoldByChannel(
		Long id,
		String title,
		String username,
		String status,
		Set<PostTraceResponse> promotingPosts
	) {
	}
}
