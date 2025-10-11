package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

	private String link;

	private String content;

	private String title;

	private String domain;

	private String siteName;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Relationship(type = "PROMOTES", direction = Relationship.Direction.OUTGOING)
	@JsonManagedReference
	private Set<Promote> promotesChannels;

	@Relationship(type = "SIMILAR", direction = Relationship.Direction.OUTGOING)
	@JsonIgnoreProperties({"promotesChannels", "similarPosts"})
	private Set<PostNode> similarPosts;

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Promote {

		@Id
		@GeneratedValue
		private Long id;

		@TargetNode
		private ChannelNode channel;
	}
}
