package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.out.mapper.ChannelSimilarityMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelSimilarityDocument;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository.ChannelSimilarityMongoRepository;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.ChannelSimilarityMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChannelSimilarityRepositoryAdapter implements ChannelSimilarityMongoPort {

	private final ChannelSimilarityMongoRepository channelSimilarityMongoRepository;
	private final ChannelSimilarityMongoMapper channelSimilarityPersistenceMapper;

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
