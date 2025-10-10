package com.vigilante.retriever.v1.argot.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.argot.adapter.out.persistence.mongo.document.ArgotDocument;

@Repository
public interface ArgotMongoRepository extends MongoRepository<ArgotDocument, String> {

	// argot으로 조회 (은어) (포함) ; slang -> name 으로 변경
	@Query("{ 'argot':  { $regex:  ?0, $options: 'i' } }")
	List<ArgotDocument> findByArgot(String argot);
}
