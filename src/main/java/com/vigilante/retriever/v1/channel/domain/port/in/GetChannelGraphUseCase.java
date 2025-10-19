package com.vigilante.retriever.v1.channel.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

public interface GetChannelGraphUseCase {

	List<ChannelGraphView> findAll();

	List<ChannelGraphView> findAllWithSells();
}
