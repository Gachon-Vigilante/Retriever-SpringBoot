package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;

public interface ChannelMongoPort {

	List<ChannelEntity> findAll();

	Optional<ChannelEntity> findById(String id);

	// TODO: 로직 재설계 필요
	// Optional<ChannelEntity> findByLink(String link);

	List<ChannelEntity> findByTitleContaining(String title);

	boolean existsById(String id);
}
