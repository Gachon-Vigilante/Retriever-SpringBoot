package com.vigilante.retriever.v1.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.user.application.query.UserMongoQuery;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;
import com.vigilante.retriever.v1.user.domain.port.in.GetUserUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

	private final UserMongoQuery userMongoQuery;

	@Override
	public List<UserEntity> getAllUsers() {
		return userMongoQuery.findAll();
	}
}

