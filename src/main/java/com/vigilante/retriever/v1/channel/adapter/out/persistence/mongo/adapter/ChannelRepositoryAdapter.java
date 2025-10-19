package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.out.mapper.ChannelMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelDocument;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository.ChannelMongoRepository;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChannelRepositoryAdapter implements ChannelMongoPort {

	private final ChannelMongoRepository channelMongoRepository;
	private final ChannelMongoMapper channelMongoMapper;

	@Override
	public List<ChannelEntity> findAll() {
		List<ChannelDocument> allChannelInfo = channelMongoRepository.findAll();
		return channelMongoMapper.getEntityList(allChannelInfo);
	}

	@Override
	public Optional<ChannelEntity> findByChannelId(Long channelId) {
		return channelMongoRepository.findByChannelId(channelId).map(channelMongoMapper::toEntity);
	}

	@Override
	public List<ChannelEntity> findByTitleContaining(String title) {
		List<ChannelDocument> channelInfoList = channelMongoRepository.findByTitleContaining(title);
		return channelMongoMapper.getEntityList(channelInfoList);
	}
}
