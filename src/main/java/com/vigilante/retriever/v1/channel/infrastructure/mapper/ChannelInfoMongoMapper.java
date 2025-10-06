package com.vigilante.retriever.v1.channel.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelInfoEntity;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.document.ChannelInfoDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelInfoMongoMapper extends GenericMongoMapper<ChannelInfoDocument, ChannelInfoEntity> {

	@Override
	ChannelInfoDocument toDocument(ChannelInfoEntity entity);

	@Override
	ChannelInfoEntity toEntity(ChannelInfoDocument document);

	@Override
	List<ChannelInfoDocument> getDocumentList(List<ChannelInfoEntity> entityList);

	@Override
	List<ChannelInfoEntity> getEntityList(List<ChannelInfoDocument> dodumentList);
}
