package com.vigilante.retriever.v1.drug.domain.enums;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrugErrorCode implements BaseCode {
	DRUG_NOT_FOUND("DRUG-4041", "해당하는 챗봇을 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
