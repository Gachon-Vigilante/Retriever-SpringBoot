package com.vigilante.retriever.v1.drug.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMapper;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;
import com.vigilante.retriever.v1.drug.infrastructure.persistence.document.DrugDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface DrugPersistenceMapper extends GenericMapper<DrugDocument, DrugEntity> {

	@Override
	DrugDocument toDocument(DrugEntity entity);

	@Override
	DrugEntity toEntity(DrugDocument document);

	@Override
	List<DrugDocument> getDocumentList(List<DrugEntity> entityList);

	@Override
	List<DrugEntity> getEntityList(List<DrugDocument> dodumentList);
}
