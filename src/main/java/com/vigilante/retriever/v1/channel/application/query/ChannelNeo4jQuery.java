package com.vigilante.retriever.v1.channel.application.query;

import static com.vigilante.retriever.v1.channel.domain.code.ChannelErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelNeo4jPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChannelNeo4jQuery {

	private final ChannelNeo4jPort channelNeo4jPort;

	ChannelGraphView getById(Long id) {
		return channelNeo4jPort.findById(id)
			.orElseThrow(() -> new NotFoundException(CHANNEL_NOT_FOUND));
	}

	public List<ChannelGraphView> findAllWithSells() {
		return channelNeo4jPort.findAllWithSells();
	}

	public List<ChannelGraphView> findAll() {
		return channelNeo4jPort.findAll();
	}
}
