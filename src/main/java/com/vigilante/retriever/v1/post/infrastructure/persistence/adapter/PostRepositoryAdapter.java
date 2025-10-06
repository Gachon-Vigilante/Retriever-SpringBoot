package com.vigilante.retriever.v1.post.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.out.PostPersistencePort;
import com.vigilante.retriever.v1.post.infrastructure.mapper.PostPersistenceMapper;
import com.vigilante.retriever.v1.post.infrastructure.persistence.document.PostDocument;
import com.vigilante.retriever.v1.post.infrastructure.persistence.repository.PostMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryAdapter implements PostPersistencePort {

	private final PostMongoRepository postMongoRepository;
	private final PostPersistenceMapper postPersistenceMapper;

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
