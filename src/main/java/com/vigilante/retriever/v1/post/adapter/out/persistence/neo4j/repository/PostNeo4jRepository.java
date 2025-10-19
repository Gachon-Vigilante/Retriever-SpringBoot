package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node.PostNode;

@Repository
public interface PostNeo4jRepository extends Neo4jRepository<PostNode, String> {

	Optional<PostNode> findByPostId(String postId);

	// stream
	@Query("""
		    MATCH (p:Post)
		    OPTIONAL MATCH (p)-[pr:PROMOTES]->(c:Channel)
		    OPTIONAL MATCH (p)-[sim:SIMILAR]->(sp:Post)
		    RETURN p, collect(pr), collect(c), collect(sim), collect(sp)
		""")
	Stream<PostNode> streamAllWithPromotesAndSimilar();

	@Query("""
			MATCH (p:Post)
			WHERE p.content = $content AND p.link = $link AND p.postId IS NULL
			SET p.postId = $postId
			RETURN COUNT(p)
		""")
	int updatePostIdByContentAndLink(String content, String link, String postId);
}
