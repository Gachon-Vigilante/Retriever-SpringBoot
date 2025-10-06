package com.vigilante.retriever.v1.channel.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;
import com.vigilante.retriever.v1.channel.infrastructure.persistence.mongo.document.ChannelSimilarityDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChannelSimilarityMongoMapper
	extends GenericMongoMapper<ChannelSimilarityDocument, ChannelSimilarityEntity> {

	@Override
	ChannelSimilarityDocument toDocument(ChannelSimilarityEntity entity);

	@Override
	ChannelSimilarityEntity toEntity(ChannelSimilarityDocument document);

	@Override
	List<ChannelSimilarityDocument> getDocumentList(List<ChannelSimilarityEntity> entityList);

	@Override
	List<ChannelSimilarityEntity> getEntityList(List<ChannelSimilarityDocument> dodumentList);
}
