package com.vigilante.retriever.v1.argot.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.argot.adapter.out.persistence.mongo.document.ArgotDocument;
import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ArgotMongoMapper extends GenericMongoMapper<ArgotDocument, ArgotEntity> {

	@Override
	ArgotDocument toDocument(ArgotEntity entity);

	@Override
	ArgotEntity toEntity(ArgotDocument document);

	@Override
	List<ArgotDocument> getDocumentList(List<ArgotEntity> entityList);

	@Override
	List<ArgotEntity> getEntityList(List<ArgotDocument> documentList);
}
