package com.vigilante.retriever.v1.drug.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.infrastructure.persistence.document.DrugDocument;

@Repository
public interface DrugMongoRepository extends MongoRepository<DrugDocument, String> {
}
