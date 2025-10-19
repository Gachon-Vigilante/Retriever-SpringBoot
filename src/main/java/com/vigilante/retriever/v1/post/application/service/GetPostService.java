package com.vigilante.retriever.v1.post.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.post.application.query.PostMongoQuery;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPostService implements GetPostUseCase {

	private final PostMongoQuery postMongoQuery;

	@Override
	public Page<PostEntity> findAll(Pageable pageable) {
		return postMongoQuery.findAll(pageable);
	}

	@Override
	public PostEntity getById(String id) {
		return postMongoQuery.getById(id);
	}

	@Override
	public List<PostEntity> findByTitleContaining(String title) {
		return postMongoQuery.findByTitleContaining(title);
	}
}
