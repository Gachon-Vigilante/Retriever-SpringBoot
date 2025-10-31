package com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.node.ArgotNode;

@Repository
public interface ArgotNeo4jRepository extends Neo4jRepository<ArgotNode, String> {

	@Query("""
		    MATCH (a: Argot)
		    OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
		    RETURN a, collect(rf), collect(d)
		""")
	List<ArgotNode> findAllWithRefersTo();

	@Query("""
		MATCH (a:Argot {name: $name})
		OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
		OPTIONAL MATCH (c:Channel)-[sell:SELLS]->(a)
		OPTIONAL MATCH (p:Post)-[pr:PROMOTES]->(c)
		OPTIONAL MATCH (p)-[sim:SIMILAR_TO]->(sp:Post)
		RETURN a, collect(rf), collect(d), collect(c), collect(sell), collect(p), collect(pr), collect(sim), collect(sp)
		""")
	Optional<ArgotNode> findArgotWithAllRelations(@Param("name") String name);
}
