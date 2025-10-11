package com.vigilante.retriever.v1.post.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.post.domain.entity.PostEntity;

public interface PostMongoPort {

	PostEntity save(PostEntity post);

	List<PostEntity> findAll();

	Optional<PostEntity> findById(String id);

	List<PostEntity> findByTitleContaining(String title);

	// TODO: 로직 재설계 필요
	// List<PostEntity> findByPromoChannelId(String promoChannelId);
	//
	// List<PostEntity> findByAuthor(String author);

	List<PostEntity> findByLinkOrderByDiscoveredAtAsc(String link);
}
