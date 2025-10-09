package com.vigilante.retriever.v1.report.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;
import com.vigilante.retriever.v1.report.infrastructure.persistence.mongo.document.ReportDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ReportMongoMapper extends GenericMongoMapper<ReportDocument, ReportEntity> {

	@Override
	ReportDocument toDocument(ReportEntity entity);

	@Override
	ReportEntity toEntity(ReportDocument document);

	@Override
	List<ReportDocument> getDocumentList(List<ReportEntity> entityList);

	@Override
	List<ReportEntity> getEntityList(List<ReportDocument> documentList);
}
