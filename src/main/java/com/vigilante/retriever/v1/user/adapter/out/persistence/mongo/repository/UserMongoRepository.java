package com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.document.UserDocument;

@Repository
public interface UserMongoRepository extends MongoRepository<UserDocument, String> {

	Optional<UserDocument> findByLoginId(String loginId);

	boolean existsByLoginId(String loginId);
}
