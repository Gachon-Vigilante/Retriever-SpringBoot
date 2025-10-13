package com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.document.DrugDocument;

@Repository
public interface DrugMongoRepository extends MongoRepository<DrugDocument, String> {

	// argots.name(은어 이름)으로 포함 검색 (대소문자 무시)
	@Query("{ 'argots.name': { $regex: ?0, $options: 'i' } }")
	List<DrugDocument> findByArgot(String argot);
}

