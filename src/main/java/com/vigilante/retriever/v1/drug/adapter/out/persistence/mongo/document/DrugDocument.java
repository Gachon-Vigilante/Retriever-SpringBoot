package com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "drugs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DrugDocument {

	@Id
	private String id;

	@Field("drugbank_id")
	private String drugBankId;

	private String name;

	@Field("drug_type")
	private String drugType;

	@Field("english_name")
	private String EnglishName;

	private List<Argot> argots;

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Argot {

		private String name;

		private String description;
	}
}
