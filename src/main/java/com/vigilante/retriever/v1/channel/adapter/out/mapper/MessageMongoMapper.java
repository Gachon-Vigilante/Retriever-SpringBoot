package com.vigilante.retriever.v1.channel.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.MessageDocument;
import com.vigilante.retriever.v1.channel.domain.entity.MessageEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface MessageMongoMapper extends GenericMongoMapper<MessageDocument, MessageEntity> {

	@Override
	MessageDocument toDocument(MessageEntity entity);

	@Override
	MessageEntity toEntity(MessageDocument document);

	@Override
	List<MessageDocument> getDocumentList(List<MessageEntity> entityList);

	@Override
	List<MessageEntity> getEntityList(List<MessageDocument> documentList);
}
