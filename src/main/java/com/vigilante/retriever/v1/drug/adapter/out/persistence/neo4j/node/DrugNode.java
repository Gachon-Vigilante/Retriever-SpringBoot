package com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.node;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

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
	@Property("drug_id")
	private String drugId;

	private String name;

	@Property("english_name")
	private String englishName;

	@Property("drug_type")
	private String drugType;
}
