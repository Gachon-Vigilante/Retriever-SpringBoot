package com.vigilante.retriever.v1.post.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.infrastructure.persistence.mongo.document.PostDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PostMongoMapper extends GenericMongoMapper<PostDocument, PostEntity> {

	@Override
	PostDocument toDocument(PostEntity entity);

	@Override
	PostEntity toEntity(PostDocument document);

	@Override
	List<PostDocument> getDocumentList(List<PostEntity> entityList);

	@Override
	List<PostEntity> getEntityList(List<PostDocument> dodumentList);
}
