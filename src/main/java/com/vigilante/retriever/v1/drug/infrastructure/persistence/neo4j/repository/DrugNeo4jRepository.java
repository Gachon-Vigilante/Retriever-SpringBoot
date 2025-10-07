package com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.node.DrugNode;

@Repository
public interface DrugNeo4jRepository extends Neo4jRepository<DrugNode, String> {
}
