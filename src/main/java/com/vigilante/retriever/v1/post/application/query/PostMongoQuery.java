package com.vigilante.retriever.v1.post.application.query;

import static com.vigilante.retriever.v1.post.domain.code.PostErrorCode.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.out.PostMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class PostMongoQuery {

	private final PostMongoPort postMongoPort;

	public Page<PostEntity> findAll(Pageable pageable) {
		return postMongoPort.findAll(pageable);
	}

	public PostEntity getById(String id) {
		return postMongoPort.findById(id).orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));
	}

	public List<PostEntity> findByTitleContaining(String title) {
		return postMongoPort.findByTitleContaining(title);
	}

	public List<PostEntity> findByLinkOrderByCreatedAtAsc(String link) {
		return postMongoPort.findByLinkOrderByDiscoveredAtAsc(link);
	}
}
