package com.vigilante.retriever.v1.channel.infrastructure.persistence.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.channel.infrastructure.persistence.neo4j.node.ChannelNode;

@Repository
public interface ChannelNeo4jRepository extends Neo4jRepository<ChannelNode, Long> {

	@Query("""
		    MATCH (c:ChannelNode)
		    OPTIONAL MATCH (c)-[sell:SELLS]->(a:Argot)
		    RETURN c, collect(sell), collect(a)
		""")
	List<ChannelNode> findAllWithSells();
}
