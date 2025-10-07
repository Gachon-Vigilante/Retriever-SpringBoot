package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

public interface ChannelNeo4jPort {

	Optional<ChannelGraphView> findById(Long id);

	List<ChannelGraphView> findAllWithSells();
}
