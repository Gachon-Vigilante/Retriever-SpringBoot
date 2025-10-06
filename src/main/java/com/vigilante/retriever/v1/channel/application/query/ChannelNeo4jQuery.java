package com.vigilante.retriever.v1.channel.application.query;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.exception.ChannelNotFoundException;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelNeo4jPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelNeo4jQuery {

	private final ChannelNeo4jPort channelNeo4jPort;

	ChannelGraphView getById(Long id) {
		return channelNeo4jPort.findById(id)
			.orElseThrow(ChannelNotFoundException::new);
	}

	public java.util.List<ChannelGraphView> findAllWithSells() {
		return channelNeo4jPort.findAllWithSells();
	}
}
