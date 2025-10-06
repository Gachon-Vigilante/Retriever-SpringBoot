package com.vigilante.retriever.v1.report.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;

public interface ReportPersistencePort {

	List<ReportEntity> findAll();

	Optional<ReportEntity> findById(String id);

	List<ReportEntity> findByChannelId(long channelId);
}
