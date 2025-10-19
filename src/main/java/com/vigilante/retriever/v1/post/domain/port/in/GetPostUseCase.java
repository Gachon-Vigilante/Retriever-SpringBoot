package com.vigilante.retriever.v1.post.domain.port.in;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vigilante.retriever.v1.post.domain.entity.PostEntity;

public interface GetPostUseCase {

	Page<PostEntity> findAll(Pageable pageable);

	PostEntity getById(String id);

	List<PostEntity> findByTitleContaining(String title);
}
