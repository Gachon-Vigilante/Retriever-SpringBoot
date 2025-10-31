package com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("""
		MATCH (c:Channel {channel_id: $channelId})
				OPTIONAL MATCH (p:Post)-[pr:PROMOTES]->(c)
				OPTIONAL MATCH (p)-[sim:SIMILAR_TO]->(sp:Post)
				OPTIONAL MATCH (c)-[sell:SELLS]->(a:Argot)
				OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
				RETURN c, collect(p), collect(pr), collect(sim), collect(sp), collect(sell), collect(a), collect(rf), collect(d);
		""")
	Optional<ChannelNode> findChannelWithAllRelations(@Param("channelId") Long channelId);
}
