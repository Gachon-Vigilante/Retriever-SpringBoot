package com.vigilante.retriever.v1.post.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.post.infrastructure.persistence.document.PostDocument;

@Repository
public interface PostMongoRepository extends MongoRepository<PostDocument, String> {

	// 제목에 포함되는 것
	List<PostDocument> findByTitleContaining(String title);

	// 홍보하는 채널 아이디로 조회
	List<PostDocument> findByPromoChannelId(String promoChannelId);

	// 게시글 작성자 이름으로 조회
	List<PostDocument> findByAuthor(String author);

	// 게시글 링크로 조회 (createdAt ASC 정렬 고정)
	List<PostDocument> findByLinkOrderByCreatedAtAsc(String link);
}
