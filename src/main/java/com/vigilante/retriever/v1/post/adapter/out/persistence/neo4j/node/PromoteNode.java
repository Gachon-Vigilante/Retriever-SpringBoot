package com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.node;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PromoteNode {

	@Id
	@GeneratedValue
	private Long id;

	@TargetNode
	private ChannelNode channel;
}
