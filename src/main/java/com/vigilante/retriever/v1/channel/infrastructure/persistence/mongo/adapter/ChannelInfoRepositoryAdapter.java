package com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelInfoEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelInfoMongoPort;
import com.vigilante.retriever.v1.channel.infrastructure.mapper.ChannelInfoMongoMapper;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.document.ChannelInfoDocument;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.repository.ChannelInfoMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChannelInfoRepositoryAdapter implements ChannelInfoMongoPort {

	private final ChannelInfoMongoRepository channelInfoMongoRepository;
	private final ChannelInfoMongoMapper channelInfoPersistenceMapper;

	@Override
	public List<ChannelInfoEntity> findAll() {
		List<ChannelInfoDocument> allChannelInfo = channelInfoMongoRepository.findAll();
		return channelInfoPersistenceMapper.getEntityList(allChannelInfo);
	}

	@Override
	public Optional<ChannelInfoEntity> findById(String id) {
		return channelInfoMongoRepository.findById(id).map(channelInfoPersistenceMapper::toEntity);
	}

	@Override
	public Optional<ChannelInfoEntity> findByLink(String link) {
		return channelInfoMongoRepository.findByLink(link).map(channelInfoPersistenceMapper::toEntity);
	}

	@Override
	public List<ChannelInfoEntity> findByTitleContaining(String title) {
		List<ChannelInfoDocument> channelInfoList = channelInfoMongoRepository.findByTitleContaining(title);
		return channelInfoPersistenceMapper.getEntityList(channelInfoList);
	}

	@Override
	public boolean existsById(String id) {
		return channelInfoMongoRepository.existsById(id);
	}
}
