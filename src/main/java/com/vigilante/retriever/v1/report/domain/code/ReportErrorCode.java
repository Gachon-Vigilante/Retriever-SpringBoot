package com.vigilante.retriever.v1.report.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportErrorCode implements BaseCode {
	REPORT_NOT_FOUND("REPORT-4041", "해당하는 Ai 채널 리포트를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
