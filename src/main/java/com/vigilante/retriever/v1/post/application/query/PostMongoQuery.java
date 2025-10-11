package com.vigilante.retriever.v1.post.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.exception.PostNotFoundException;
import com.vigilante.retriever.v1.post.domain.port.out.PostMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class PostMongoQuery {

	private final PostMongoPort postMongoPort;

	public List<PostEntity> findAll() {
		return postMongoPort.findAll();
	}

	public PostEntity getById(String id) {
		return postMongoPort.findById(id)
			.orElseThrow(PostNotFoundException::new);
	}

	public List<PostEntity> findByTitleContaining(String title) {
		return postMongoPort.findByTitleContaining(title);
	}

	// TODO: 로직 재설계 필요
	// public List<PostEntity> findByPromoChannelId(String promoChannelId) {
	// 	return postMongoPort.findByPromoChannelId(promoChannelId);
	// }
	//
	// public List<PostEntity> findByAuthor(String author) {
	// 	return postMongoPort.findByAuthor(author);
	// }

	public List<PostEntity> findByLinkOrderByCreatedAtAsc(String link) {
		return postMongoPort.findByLinkOrderByDiscoveredAtAsc(link);
	}
}
