package com.vigilante.retriever.v1.report.domain.exception;

import static com.vigilante.retriever.v1.report.domain.code.ReportErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ReportNotFoundException extends NotFoundException {

	public ReportNotFoundException() {
		super(REPORT_NOT_FOUND);
	}
}
