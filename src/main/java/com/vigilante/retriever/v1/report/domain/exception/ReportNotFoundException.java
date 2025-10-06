package com.vigilante.retriever.v1.report.domain.exception;

import static com.vigilante.retriever.v1.report.domain.enums.ReportErrorCode.*;

import com.vigilante.retriever.global.exception.NotFoundException;

public class ReportNotFoundException extends NotFoundException {

	public ReportNotFoundException() {
		super(REPORT_NOT_FOUND);
	}
}
