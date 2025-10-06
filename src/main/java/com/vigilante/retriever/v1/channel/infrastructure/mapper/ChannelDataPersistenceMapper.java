package com.vigilante.retriever.v1.channel.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMapper;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelDataEntity;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.document.ChannelDataDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelDataPersistenceMapper extends GenericMapper<ChannelDataDocument, ChannelDataEntity> {

	@Override
	ChannelDataDocument toDocument(ChannelDataEntity entity);

	@Override
	ChannelDataEntity toEntity(ChannelDataDocument document);

	@Override
	List<ChannelDataDocument> getDocumentList(List<ChannelDataEntity> entityList);

	@Override
	List<ChannelDataEntity> getEntityList(List<ChannelDataDocument> dodumentList);
}
