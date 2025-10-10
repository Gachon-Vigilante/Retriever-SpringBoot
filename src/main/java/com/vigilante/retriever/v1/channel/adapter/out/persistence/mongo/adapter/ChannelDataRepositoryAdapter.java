package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.out.mapper.ChannelDataMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelDataDocument;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository.ChannelDataMongoRepository;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelDataMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChannelDataRepositoryAdapter implements ChannelDataMongoPort {

	private final ChannelDataMongoRepository channelDataMongoRepository;
	private final ChannelDataMongoMapper channelDataPersistenceMapper;

	@Override
	public List<ChannelDataEntity> findAll() {
		List<ChannelDataDocument> allChannelData = channelDataMongoRepository.findAll();
		return channelDataPersistenceMapper.getEntityList(allChannelData);
	}

	@Override
	public Optional<ChannelDataEntity> findById(String id) {
		return channelDataMongoRepository.findById(id).map(channelDataPersistenceMapper::toEntity);
	}

	@Override
	public List<ChannelDataEntity> findByChannelId(long channelId) {
		List<ChannelDataDocument> channelDataList = channelDataMongoRepository.findByChannelId(channelId);
		return channelDataPersistenceMapper.getEntityList(channelDataList);
	}
}
