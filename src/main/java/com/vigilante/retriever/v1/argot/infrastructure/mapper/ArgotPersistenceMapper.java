package com.vigilante.retriever.v1.argot.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMapper;
import com.vigilante.retriever.v1.argot.domain.entity.ArgotEntity;
import com.vigilante.retriever.v1.argot.infrastructure.persistence.document.ArgotDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ArgotPersistenceMapper extends GenericMapper<ArgotDocument, ArgotEntity> {

	@Override
	ArgotDocument toDocument(ArgotEntity entity);

	@Override
	ArgotEntity toEntity(ArgotDocument document);

	@Override
	List<ArgotDocument> getDocumentList(List<ArgotEntity> entityList);

	@Override
	List<ArgotEntity> getEntityList(List<ArgotDocument> dodumentList);
}
