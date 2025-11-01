package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node.PostNode;

@Repository
public interface PostNeo4jRepository extends Neo4jRepository<PostNode, String> {

	Optional<PostNode> findByPostId(String postId);

	// stream
	@Query("""
		MATCH (p:Post)
		OPTIONAL MATCH (p)-[pr:PROMOTES]->(c:Channel)
		OPTIONAL MATCH (p)-[sr:SIMILAR_TO]->(sp:Post)
		RETURN p, collect(DISTINCT pr), collect(DISTINCT c), collect(DISTINCT sr), collect(DISTINCT sp)
		""")
	Stream<PostNode> streamAllWithPromotesAndSimilar();

	@Query("""
        MATCH (p:Post)
        WHERE p.cluster = $cluster
        OPTIONAL MATCH (p)-[pr:PROMOTES]->(c:Channel)
        OPTIONAL MATCH (p)-[sim:SIMILAR]->(sp:Post)
        RETURN p, collect(pr), collect(c), collect(sim), collect(sp)
    """)
	Stream<PostNode> streamByClusterWithPromotesAndSimilar(@Param("cluster") int cluster);

	@Query("""
		MATCH (p:Post {post_id: $postId})
		OPTIONAL MATCH (p)-[sim:SIMILAR_TO]->(sp:Post)
		OPTIONAL MATCH (p)-[pr:PROMOTES]->(c:Channel)
		OPTIONAL MATCH (c)-[sell:SELLS]->(a:Argot)
		OPTIONAL MATCH (a)-[rf:REFERS_TO]->(d:Drug)
		RETURN p, collect(sim), collect(sp), collect(pr), collect(c), collect(sell), collect(a), collect(rf), collect(d)
	""")
	Optional<PostNode> findPostWithAllRelations(@Param("postId") String postId);

	@Query("""
			MATCH (p:Post)
			WHERE p.content = $content AND p.link = $link AND p.postId IS NULL
			SET p.postId = $postId
			RETURN COUNT(p)
		""")
	int updatePostIdByContentAndLink(String content, String link, String postId);
}
