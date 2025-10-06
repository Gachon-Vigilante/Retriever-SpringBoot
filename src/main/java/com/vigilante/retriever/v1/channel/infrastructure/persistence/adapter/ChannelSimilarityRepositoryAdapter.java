package com.vigilante.retriever.v1.channel.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelSimilarityPersistencePort;
import com.vigilante.retriever.v1.channel.infrastructure.mapper.ChannelSimilarityPersistenceMapper;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.document.ChannelSimilarityDocument;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.repository.ChannelSimilarityMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChannelSimilarityRepositoryAdapter implements ChannelSimilarityPersistencePort {

	private final ChannelSimilarityMongoRepository channelSimilarityMongoRepository;
	private final ChannelSimilarityPersistenceMapper channelSimilarityPersistenceMapper;

	@Override
	public List<ChannelSimilarityEntity> findAll() {
		List<ChannelSimilarityDocument> allChannelSimilarity = channelSimilarityMongoRepository.findAll();
		return channelSimilarityPersistenceMapper.getEntityList(allChannelSimilarity);
	}

	@Override
	public Optional<ChannelSimilarityEntity> findById(String id) {
		return channelSimilarityMongoRepository.findById(id).map(channelSimilarityPersistenceMapper::toEntity);
	}

	@Override
	public Optional<ChannelSimilarityEntity> findByChannelId(long channelId) {
		return channelSimilarityMongoRepository.findByChannelId(channelId)
			.map(channelSimilarityPersistenceMapper::toEntity);
	}
}
