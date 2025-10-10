package com.vigilante.retriever.v1.user.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.user.adapter.out.persistence.mongo.document.UserDocument;
import com.vigilante.retriever.v1.user.domain.entity.UserEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMongoMapper extends GenericMongoMapper<UserDocument, UserEntity> {

	@Override
	UserDocument toDocument(UserEntity entity);

	@Override
	UserEntity toEntity(UserDocument document);

	@Override
	List<UserDocument> getDocumentList(List<UserEntity> entityList);

	@Override
	List<UserEntity> getEntityList(List<UserDocument> documentList);
}
