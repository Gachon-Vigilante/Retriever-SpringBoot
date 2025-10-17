package com.vigilante.retriever.v1.user.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.user.domain.entity.UserEntity;

public interface GetUserUseCase {

	List<UserEntity> getAllUsers();
}
