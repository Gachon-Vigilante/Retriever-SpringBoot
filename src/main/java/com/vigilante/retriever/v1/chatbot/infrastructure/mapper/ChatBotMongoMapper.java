package com.vigilante.retriever.v1.chatbot.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;
import com.vigilante.retriever.v1.chatbot.infrastructure.persistence.mongo.document.ChatBotDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChatBotMongoMapper extends GenericMongoMapper<ChatBotDocument, ChatBotEntity> {

	@Override
	ChatBotDocument toDocument(ChatBotEntity entity);

	@Override
	ChatBotEntity toEntity(ChatBotDocument document);

	@Override
	List<ChatBotDocument> getDocumentList(List<ChatBotEntity> entityList);

	@Override
	List<ChatBotEntity> getEntityList(List<ChatBotDocument> documentList);
}
