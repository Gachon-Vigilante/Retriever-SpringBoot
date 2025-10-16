package com.vigilante.retriever.v1.channel.domain.graphview;

import java.util.Set;

import com.vigilante.retriever.v1.drug.domain.graphview.ArgotGraphView;

import lombok.Builder;

@Builder
public record ChannelGraphView(
	Long id,
	String title,
	String username,
	String status,
	int promotedCount,
	Set<ArgotGraphView> sellsArgots
) {
}
