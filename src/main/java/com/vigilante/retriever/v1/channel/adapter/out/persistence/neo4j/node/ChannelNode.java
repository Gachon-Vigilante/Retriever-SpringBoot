package com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.node.ArgotNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Node("Channel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelNode {

	@Id
	private Long id;

	private String title;

	private String username;

	private String status;

	private int promotedCount;

	@Relationship(type = "SELLS", direction = Relationship.Direction.OUTGOING)
	@JsonIgnoreProperties("channels")
	private Set<ArgotNode> sellsArgots;
}
