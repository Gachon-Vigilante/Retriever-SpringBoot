package com.vigilante.retriever.v1.channel.application.service;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.channel.application.query.ChannelNeo4jQuery;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.in.GetChannelTraceUseCase;
import com.vigilante.retriever.v1.channel.domain.vo.ChannelTraceVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetChannelTraceService implements GetChannelTraceUseCase {

	private final ChannelNeo4jQuery channelNeo4jQuery;

	@Override
	public ChannelTraceVO getChannelPostTrace(Long channelId) {
		ChannelGraphView channel = channelNeo4jQuery.findChannelWithAllRelations(channelId);
		return ChannelTraceVO.create(channel);
	}
}
