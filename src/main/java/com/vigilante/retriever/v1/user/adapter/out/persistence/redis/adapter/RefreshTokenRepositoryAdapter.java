package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.entity.RefreshTokenRedisEntity;
import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.repository.RefreshTokenRedisRepository;
import com.vigilante.retriever.v1.user.domain.port.out.RefreshTokenRedisPort;
import com.vigilante.retriever.v1.user.domain.vo.RefreshToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRedisPort {

	private final RefreshTokenRedisRepository repository;

	@Override
	public void save(RefreshToken token) {
		RefreshTokenRedisEntity entity = RefreshTokenRedisEntity.from(token);
		repository.save(entity);
	}

	@Override
	public Optional<RefreshToken> findByTokenValue(String tokenValue) {
		return repository.findByRefreshToken(tokenValue)
			.map(RefreshTokenRedisEntity::toDomain);
	}

	@Override
	public Optional<RefreshToken> findByUserId(String userId) {
		return repository.findById(userId)
			.map(RefreshTokenRedisEntity::toDomain);
	}

	@Override
	public void deleteByUserId(String userId) {
		repository.deleteById(userId);
	}

	@Override
	public boolean existsByUserId(String userId) {
		return repository.existsById(userId);
	}
}
