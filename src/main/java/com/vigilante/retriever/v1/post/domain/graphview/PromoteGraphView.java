package com.vigilante.retriever.v1.post.domain.graphview;

import com.vigilante.retriever.v1.channel.infrastructure.persistence.neo4j.node.ChannelNode;

import lombok.Builder;

@Builder
public record PromoteGraphView(
	String id,
	ChannelNode channel
) {
}
