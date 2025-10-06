package com.vigilante.retriever.v1.post.infrastructure.persistence.neo4j.node;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Node("Post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostNode {

	@Id
	private String postId;

	private int cluster;

	private String content;

	private String link;

	private String siteName;

	private String createdAt;

	private String updatedAt;

	@Relationship(type = "PROMOTES", direction = Relationship.Direction.OUTGOING)
	@JsonManagedReference
	private Set<PromoteNode> promotesChannels;

	@Relationship(type = "SIMILAR", direction = Relationship.Direction.OUTGOING)
	@JsonIgnoreProperties({"promotesChannels", "similarPosts"})
	private Set<PostNode> similarPosts;
}
