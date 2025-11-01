package com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node.DrugNode;

@Repository
public interface DrugNeo4jRepository extends Neo4jRepository<DrugNode, String> {

	@Query("""
		MATCH (d:Drug {drugbank_id: $drugBankId})
		OPTIONAL MATCH (a:Argot)-[rf:REFERS_TO]->(d)
		OPTIONAL MATCH (c:Channel)-[sell:SELLS]->(a)
		OPTIONAL MATCH (p:Post)-[pr:PROMOTES]->(c)
		OPTIONAL MATCH (p)-[sim:SIMILAR_TO]->(sp:Post)
		RETURN d, collect(a), collect(rf), collect(c), collect(sell), collect(p), collect(pr), collect(sim), collect(sp)
		""")
	Optional<DrugNode> findDrugWithAllRelationships(@Param("drugBankId") String drugBankId);
}
