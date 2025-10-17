package com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.user.adapter.out.mapper.UserMongoMapper;
import com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.document.UserDocument;
import com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.repository.UserMongoRepository;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.out.UserMongoPort;

import lombok.RequiredArgsConstructor;

@Component
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

	@Override
	public List<UserEntity> findAll() {
		return userMongoRepository.findAll()
			.stream()
			.map(userPersistenceMapper::toEntity)
			.toList();
	}

	@Override
	public void delete(UserEntity userEntity) {
		UserDocument document = userPersistenceMapper.toDocument(userEntity);
		userMongoRepository.delete(document);
	}
}
