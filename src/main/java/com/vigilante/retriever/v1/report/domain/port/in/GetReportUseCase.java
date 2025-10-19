package com.vigilante.retriever.v1.report.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;

public interface GetReportUseCase {

	List<ReportEntity> findAll();

	ReportEntity getById( String id);

	List<ReportEntity> getByChannelId(Long channelId);
}
