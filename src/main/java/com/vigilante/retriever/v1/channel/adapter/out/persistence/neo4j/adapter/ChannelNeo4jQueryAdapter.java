package com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.out.mapper.ChannelNeo4jMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.repository.ChannelNeo4jRepository;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelNeo4jPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChannelNeo4jQueryAdapter implements ChannelNeo4jPort {

	private final ChannelNeo4jRepository channelNeo4jRepository;
	private final ChannelNeo4jMapper channelNeo4jMapper;

	@Override
	public Optional<ChannelGraphView> findById(Long id) {
		return channelNeo4jRepository.findById(id).map(channelNeo4jMapper::toGraphView);
	}

	@Override
	public List<ChannelGraphView> findAllWithSells() {
		List<ChannelNode> channelNodes = channelNeo4jRepository.findAllWithSells();
		return channelNeo4jMapper.getGraphViewList(channelNodes);
	}
}
