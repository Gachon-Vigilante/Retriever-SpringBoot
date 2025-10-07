package com.vigilante.retriever.v1.drug.domain.exception;

import static com.vigilante.retriever.v1.drug.domain.enums.DrugErrorCode.*;

import com.vigilante.retriever.global.exception.NotFoundException;

public class DrugNotFoundException extends NotFoundException {

	public DrugNotFoundException() {
		super(DRUG_NOT_FOUND);
	}
}
