package com.vigilante.retriever.v1.channel.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.channel.domain.entity.ChannelInfoEntity;

public interface ChannelInfoPersistencePort {

	List<ChannelInfoEntity> findAll();

	Optional<ChannelInfoEntity> findById(String id);

	Optional<ChannelInfoEntity> findByLink(String link);

	List<ChannelInfoEntity> findByTitleContaining(String title);

	boolean existsById(String id);
}
