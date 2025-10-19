package com.vigilante.retriever.v1.post.domain.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vigilante.retriever.v1.post.domain.entity.PostEntity;

public interface PostMongoPort {

	PostEntity save(PostEntity post);

	Page<PostEntity> findAll(Pageable pageable);

	Optional<PostEntity> findById(String id);

	List<PostEntity> findByTitleContaining(String title);

	List<PostEntity> findByLinkOrderByDiscoveredAtAsc(String link);
}
