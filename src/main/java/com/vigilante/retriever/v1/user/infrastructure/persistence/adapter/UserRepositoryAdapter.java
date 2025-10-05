package com.vigilante.retriever.v1.user.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.out.UserPersistencePort;
import com.vigilante.retriever.v1.user.infrastructure.mapper.UserPersistenceMapper;
import com.vigilante.retriever.v1.user.infrastructure.persistence.document.UserDocument;
import com.vigilante.retriever.v1.user.infrastructure.persistence.repository.UserMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPersistencePort {

	private final UserMongoRepository userMongoRepository;
	private final UserPersistenceMapper userPersistenceMapper;

	@Override
	public UserEntity save(UserEntity userEntity) {
		UserDocument toSave = userPersistenceMapper.toDocument(userEntity);
		UserDocument saved = userMongoRepository.save(toSave);
		return userPersistenceMapper.toEntity(saved);
	}

	@Override
	public Optional<UserEntity> findByLoginId(String loginId) {
		return userMongoRepository.findByLoginId(loginId).map(userPersistenceMapper::toEntity);
	}

	@Override
	public boolean existsByLoginId(String loginId) {
		return userMongoRepository.existsByLoginId(loginId);
	}
}
