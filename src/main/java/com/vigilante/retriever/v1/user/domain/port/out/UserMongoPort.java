package com.vigilante.retriever.v1.user.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.user.domain.entity.UserEntity;

public interface UserMongoPort {

	Optional<UserEntity> findByLoginId(String loginId);

	boolean existsByLoginId(String loginId);

	UserEntity save(UserEntity userEntity);

	List<UserEntity> findAll();

	void delete(UserEntity userEntity);
}
