package com.vigilante.retriever.v1.channel.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.channel.application.query.ChannelNeo4jQuery;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.in.GetChannelGraphUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetChannelGraphService implements GetChannelGraphUseCase {

	private final ChannelNeo4jQuery channelNeo4jQuery;

	@Override
	public List<ChannelGraphView> findAll() {
		return channelNeo4jQuery.findAll();
	}

	@Override
	public List<ChannelGraphView> findAllWithSells() {
		return channelNeo4jQuery.findAllWithSells();
	}
}
