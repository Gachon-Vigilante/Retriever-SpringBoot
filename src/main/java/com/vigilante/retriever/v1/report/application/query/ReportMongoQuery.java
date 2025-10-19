package com.vigilante.retriever.v1.report.application.query;

import static com.vigilante.retriever.v1.report.domain.code.ReportErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;
import com.vigilante.retriever.v1.report.domain.port.out.ReportMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ReportMongoQuery {

	private final ReportMongoPort reportMongoPort;

	public List<ReportEntity> findAll() {
		return reportMongoPort.findAll();
	}

	public ReportEntity getById(String id) {
		return reportMongoPort.findById(id)
			.orElseThrow(() -> new NotFoundException(REPORT_NOT_FOUND));
	}

	public List<ReportEntity> getByChannelId(long channelId) {
		return reportMongoPort.findByChannelId(channelId);
	}
}
