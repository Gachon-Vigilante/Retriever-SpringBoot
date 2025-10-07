package com.vigilante.retriever.v1.argot.infrastructure.persistence.neo4j.node;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.neo4j.node.DrugNode;

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
	private Long identity;

	private String name;

	private String drugId;

	@Relationship(type = "REFERS_TO", direction = Relationship.Direction.OUTGOING)
	@JsonIgnoreProperties("argots")
	private Set<DrugNode> refersDrugs;
}
