package com.vigilante.retriever.v1.user.domain.port.out;

import java.util.Optional;

import com.vigilante.retriever.v1.user.domain.vo.RefreshToken;

public interface RefreshTokenRedisPort {

	void save(RefreshToken token);

	Optional<RefreshToken> findByTokenValue(String tokenValue);

	Optional<RefreshToken> findByUserId(String userId);

	void deleteByUserId(String userId);

	boolean existsByUserId(String userId);
}
