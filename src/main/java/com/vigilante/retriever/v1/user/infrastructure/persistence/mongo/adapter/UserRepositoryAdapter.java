package com.vigilante.retriever.v1.user.infrastructure.persistence.mongo.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.out.UserMongoPort;
import com.vigilante.retriever.v1.user.infrastructure.mapper.UserMongoMapper;
import com.vigilante.retriever.v1.user.infrastructure.persistence.mongo.document.UserDocument;
import com.vigilante.retriever.v1.user.infrastructure.persistence.mongo.repository.UserMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserMongoPort {

	private final UserMongoRepository userMongoRepository;
	private final UserMongoMapper userPersistenceMapper;

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
