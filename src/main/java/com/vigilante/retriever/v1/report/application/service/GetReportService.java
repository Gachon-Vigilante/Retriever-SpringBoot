package com.vigilante.retriever.v1.report.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.report.application.query.ReportMongoQuery;
import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;
import com.vigilante.retriever.v1.report.domain.port.in.GetReportUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetReportService implements GetReportUseCase {

	private final ReportMongoQuery reportMongoQuery;

	@Override
	public List<ReportEntity> findAll() {
		return reportMongoQuery.findAll();
	}

	@Override
	public ReportEntity getById(String id) {
		return reportMongoQuery.getById(id);
	}

	@Override
	public List<ReportEntity> getByChannelId(Long channelId) {
		return reportMongoQuery.getByChannelId(channelId);
	}
}
