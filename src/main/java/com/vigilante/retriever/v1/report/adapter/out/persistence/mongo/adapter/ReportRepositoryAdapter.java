package com.vigilante.retriever.v1.report.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.report.adapter.out.mapper.ReportMongoMapper;
import com.vigilante.retriever.v1.report.adapter.out.persistence.mongo.document.ReportDocument;
import com.vigilante.retriever.v1.report.adapter.out.persistence.mongo.repository.ReportMongoRepository;
import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;
import com.vigilante.retriever.v1.report.domain.port.out.ReportMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportRepositoryAdapter implements ReportMongoPort {

	private final ReportMongoRepository reportMongoRepository;
	private final ReportMongoMapper reportPersistenceMapper;

	@Override
	public List<ReportEntity> findAll() {
		List<ReportDocument> allReport = reportMongoRepository.findAll();
		return reportPersistenceMapper.getEntityList(allReport);
	}

	@Override
	public Optional<ReportEntity> findById(String id) {
		return reportMongoRepository.findById(id).map(reportPersistenceMapper::toEntity);
	}

	@Override
	public List<ReportEntity> findByChannelId(long channelId) {
		List<ReportDocument> reportList = reportMongoRepository.findByChannelId(channelId);
		return reportPersistenceMapper.getEntityList(reportList);
	}
}
