package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.entity.RefreshTokenRedisEntity;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedisEntity, String> {

	Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
