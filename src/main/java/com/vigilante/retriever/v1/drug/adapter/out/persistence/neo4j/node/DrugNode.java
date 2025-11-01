package com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.node.ArgotNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Node("Drug")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DrugNode {

	@Id
	@Property("drugbank_id")
	private String drugBankId;

	private String name;

	@Property("english_name")
	private String englishName;

	@Property("drug_type")
	private String drugType;

	@Relationship(type = "REFERS_TO", direction = Relationship.Direction.INCOMING)
	@JsonBackReference
	private Set<ArgotNode> referredByArgots = new HashSet<>();
}
