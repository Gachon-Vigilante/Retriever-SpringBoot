package com.vigilante.retriever.v1.channel.application.command;

import com.vigilante.retriever.common.domain.annotation.CommandService;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelNeo4jPort;

import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChannelNeo4jCommand {

	private final ChannelNeo4jPort channelNeo4jPort;

	public ChannelGraphView save(ChannelGraphView channel) {
		return channelNeo4jPort.save(channel);
	}
}
