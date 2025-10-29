package com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;

@Repository
public interface ChannelNeo4jRepository extends Neo4jRepository<ChannelNode, Long> {

	@Query("""
		    MATCH (c:Channel)
		    OPTIONAL MATCH (c)-[sell:SELLS]->(a:Argot)
		    OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
		    RETURN c, collect(DISTINCT sell) as sells, collect(DISTINCT a) as argots, collect(DISTINCT rf) as refers, collect(DISTINCT d) as drugs
		""")
	List<ChannelNode> findAllWithSells();
}
