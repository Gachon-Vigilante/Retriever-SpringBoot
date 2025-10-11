package com.vigilante.retriever.v1.channel.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelDocument;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelMongoMapper extends GenericMongoMapper<ChannelDocument, ChannelEntity> {

	@Override
	ChannelDocument toDocument(ChannelEntity entity);

	@Override
	ChannelEntity toEntity(ChannelDocument document);

	@Override
	List<ChannelDocument> getDocumentList(List<ChannelEntity> entityList);

	@Override
	List<ChannelEntity> getEntityList(List<ChannelDocument> documentList);
}
