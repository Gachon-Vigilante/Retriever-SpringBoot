package com.vigilante.retriever.v1.user.domain.port.out;

import java.util.Optional;

import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;

public interface RefreshTokenRedisPort {

	RefreshTokenEntity save(RefreshTokenEntity token);

	Optional<RefreshTokenEntity> findByTokenValue(String tokenValue);

	Optional<RefreshTokenEntity> findByUserId(String userId);

	void deleteByUserId(String userId);

	boolean existsByUserId(String userId);
}
