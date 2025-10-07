package com.vigilante.retriever.v1.argot.infrastructure.persistence.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.argot.infrastructure.persistence.neo4j.node.ArgotNode;

@Repository
public interface ArgotNeo4jRepository extends Neo4jRepository<ArgotNode, Long> {

	@Query("""
		    MATCH (a: Argot)
		    OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
		    RETURN a, collect(rf), collect(d)
		""")
	List<ArgotNode> findAllWithRefersTo();
}
