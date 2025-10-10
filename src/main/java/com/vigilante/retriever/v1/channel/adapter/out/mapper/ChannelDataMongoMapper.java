package com.vigilante.retriever.v1.channel.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelDataDocument;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelDataMongoMapper extends GenericMongoMapper<ChannelDataDocument, ChannelDataEntity> {

	@Override
	ChannelDataDocument toDocument(ChannelDataEntity entity);

	@Override
	ChannelDataEntity toEntity(ChannelDataDocument document);

	@Override
	List<ChannelDataDocument> getDocumentList(List<ChannelDataEntity> entityList);

	@Override
	List<ChannelDataEntity> getEntityList(List<ChannelDataDocument> documentList);
}
