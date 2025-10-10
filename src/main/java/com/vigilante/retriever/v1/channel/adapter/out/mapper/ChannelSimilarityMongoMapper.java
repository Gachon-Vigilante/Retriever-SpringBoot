package com.vigilante.retriever.v1.channel.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.ChannelSimilarityDocument;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelSimilarityEntity;

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
	List<ChannelSimilarityEntity> getEntityList(List<ChannelSimilarityDocument> documentList);
}
