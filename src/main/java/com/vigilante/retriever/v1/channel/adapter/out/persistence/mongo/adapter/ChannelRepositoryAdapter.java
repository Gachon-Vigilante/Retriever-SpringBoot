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
	public Optional<ChannelEntity> findById(String id) {
		return channelMongoRepository.findById(id).map(channelMongoMapper::toEntity);
	}

	// TODO: 로직 재설계 필요
	// @Override
	// public Optional<ChannelEntity> findByLink(String link) {
	// 	return channelMongoRepository.findByLink(link).map(channelMongoMapper::toEntity);
	// }

	@Override
	public List<ChannelEntity> findByTitleContaining(String title) {
		List<ChannelDocument> channelInfoList = channelMongoRepository.findByTitleContaining(title);
		return channelMongoMapper.getEntityList(channelInfoList);
	}

	@Override
	public boolean existsById(String id) {
		return channelMongoRepository.existsById(id);
	}
}
