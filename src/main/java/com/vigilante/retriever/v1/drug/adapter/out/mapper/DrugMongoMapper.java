package com.vigilante.retriever.v1.drug.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.drug.adapter.out.persistence.mongo.document.DrugDocument;
import com.vigilante.retriever.v1.drug.domain.entity.DrugEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface DrugMongoMapper extends GenericMongoMapper<DrugDocument, DrugEntity> {

	@Override
	DrugDocument toDocument(DrugEntity entity);

	@Override
	DrugEntity toEntity(DrugDocument document);

	@Override
	List<DrugDocument> getDocumentList(List<DrugEntity> entityList);

	@Override
	List<DrugEntity> getEntityList(List<DrugDocument> documentList);
}
