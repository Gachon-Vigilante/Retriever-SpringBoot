package com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.node;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.node.ChannelNode;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node.DrugNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Node("Argot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgotNode {

	@Id
	private String name;

	private String description;

	@Relationship(type = "REFERS_TO", direction = Relationship.Direction.OUTGOING)
	@Property("refers_drugs")
	private Set<DrugNode> refersDrugs;

	@Relationship(type = "SELLS", direction = Relationship.Direction.INCOMING)
	@JsonBackReference
	private Set<ChannelNode> soldByChannels = new HashSet<>();
}
