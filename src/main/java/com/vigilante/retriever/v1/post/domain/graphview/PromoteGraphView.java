package com.vigilante.retriever.v1.post.domain.graphview;

import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

import lombok.Builder;

@Builder
public record PromoteGraphView(
	String id,
	ChannelGraphView channel
) {
}
