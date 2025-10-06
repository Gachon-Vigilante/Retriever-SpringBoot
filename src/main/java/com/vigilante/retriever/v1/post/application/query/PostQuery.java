package com.vigilante.retriever.v1.post.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.exception.PostNotFoundException;
import com.vigilante.retriever.v1.post.domain.port.out.PostPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class PostQuery {

	private final PostPersistencePort postPersistencePort;

	public List<PostEntity> findAll() {
		return postPersistencePort.findAll();
	}

	public PostEntity getById(String id) {
		return postPersistencePort.findById(id)
			.orElseThrow(PostNotFoundException::new);
	}

	public List<PostEntity> findByTitleContaining(String title) {
		return postPersistencePort.findByTitleContaining(title);
	}

	public List<PostEntity> findByPromoChannelId(String promoChannelId) {
		return postPersistencePort.findByPromoChannelId(promoChannelId);
	}

	public List<PostEntity> findByAuthor(String author) {
		return postPersistencePort.findByAuthor(author);
	}

	public List<PostEntity> findByLinkOrderByCreatedAtAsc(String link) {
		return postPersistencePort.findByLinkOrderByCreatedAtAsc(link);
	}
}
