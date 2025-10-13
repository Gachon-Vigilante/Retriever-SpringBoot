package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
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
	@Property("post_id")
	private String postId;

	private String title;

	private String link;

	private String domain;

	@Property("site_name")
	private String siteName;

	private String content;

	private int cluster;

	@Property("discovered_at")
	private LocalDateTime discoveredAt;

	@Property("updated_at")
	private LocalDateTime updatedAt;

	@Property("is_deleted")
	private boolean isDeleted;

	@Relationship(type = "PROMOTES", direction = Relationship.Direction.OUTGOING)
	@JsonManagedReference
	@Property("promotes_channels")
	private Set<Promote> promotesChannels;

	@Relationship(type = "SIMILAR", direction = Relationship.Direction.OUTGOING)
	@JsonIgnoreProperties({"promotesChannels", "similarPosts"})
	@Property("similar_posts")
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
