package com.vigilante.retriever.v1.post.infrastructure.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.out.PostMongoPort;
import com.vigilante.retriever.v1.post.infrastructure.mapper.PostMongoMapper;
import com.vigilante.retriever.v1.post.infrastructure.persistence.mongo.document.PostDocument;
import com.vigilante.retriever.v1.post.infrastructure.persistence.mongo.repository.PostMongoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostRepositoryAdapter implements PostMongoPort {

	private final PostMongoRepository postMongoRepository;
	private final PostMongoMapper postPersistenceMapper;

	@Override
	public PostEntity save(PostEntity post) {
		PostDocument toSave = postPersistenceMapper.toDocument(post);
		PostDocument saved = postMongoRepository.save(toSave);
		return postPersistenceMapper.toEntity(saved);
	}

	@Override
	public List<PostEntity> findAll() {
		List<PostDocument> allPost = postMongoRepository.findAll();
		return postPersistenceMapper.getEntityList(allPost);
	}

	@Override
	public Optional<PostEntity> findById(String id) {
		return postMongoRepository.findById(id).map(postPersistenceMapper::toEntity);
	}

	@Override
	public List<PostEntity> findByTitleContaining(String title) {
		List<PostDocument> postList = postMongoRepository.findByTitleContaining(title);
		return postPersistenceMapper.getEntityList(postList);
	}

	@Override
	public List<PostEntity> findByPromoChannelId(String promoChannelId) {
		List<PostDocument> postList = postMongoRepository.findByPromoChannelId(promoChannelId);
		return postPersistenceMapper.getEntityList(postList);
	}

	@Override
	public List<PostEntity> findByAuthor(String author) {
		List<PostDocument> postList = postMongoRepository.findByAuthor(author);
		return postPersistenceMapper.getEntityList(postList);
	}

	@Override
	public List<PostEntity> findByLinkOrderByCreatedAtAsc(String link) {
		List<PostDocument> postList = postMongoRepository.findByLinkOrderByCreatedAtAsc(link);
		return postPersistenceMapper.getEntityList(postList);
	}
}
