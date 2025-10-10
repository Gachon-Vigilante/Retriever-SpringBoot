package com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.document;

import org.springframework.data.mongodb.core.mapping.Document;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.infrastructure.common.document.BaseDocument;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDocument extends BaseDocument {

	private String loginId;

	private String password;

	private String name;

	private Role role;
}
