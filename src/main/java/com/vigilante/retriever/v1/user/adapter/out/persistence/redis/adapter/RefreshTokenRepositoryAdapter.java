package com.vigilante.retriever.v1.user.adapter.out.persistence.redis.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.user.adapter.out.mapper.RefreshTokenRedisMapper;
import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.hash.RefreshTokenHash;
import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.repository.RefreshTokenRedisRepository;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.port.out.RefreshTokenRedisPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRedisPort {

	private final RefreshTokenRedisRepository refreshTokenRedisRepository;
	private final RefreshTokenRedisMapper refreshTokenRedisMapper;

	@Override
	public RefreshTokenEntity save(RefreshTokenEntity token) {
		RefreshTokenHash hash = refreshTokenRedisMapper.toHash(token);
		RefreshTokenHash savedHash = refreshTokenRedisRepository.save(hash);
		return refreshTokenRedisMapper.toEntity(savedHash);
	}

	@Override
	public Optional<RefreshTokenEntity> findByTokenValue(String tokenValue) {
		return refreshTokenRedisRepository.findByRefreshToken(tokenValue).map(refreshTokenRedisMapper::toEntity);
	}

	@Override
	public Optional<RefreshTokenEntity> findByUserId(String userId) {
		return refreshTokenRedisRepository.findById(userId).map(refreshTokenRedisMapper::toEntity);
	}

	@Override
	public void deleteByUserId(String userId) {
		refreshTokenRedisRepository.deleteById(userId);
	}

	@Override
	public boolean existsByUserId(String userId) {
		return refreshTokenRedisRepository.existsById(userId);
	}
}
