package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.hash.RefreshTokenHash;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenHash, String> {

	Optional<RefreshTokenHash> findByRefreshToken(String refreshToken);
}
