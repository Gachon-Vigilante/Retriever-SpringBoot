package com.vigilante.retriever.v1.bookmark.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericMongoMapper;
import com.vigilante.retriever.v1.bookmark.adapter.out.persistence.mongo.document.BookmarkDocument;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BookmarkMongoMapper extends GenericMongoMapper<BookmarkDocument, BookmarkEntity> {

	@Override
	BookmarkDocument toDocument(BookmarkEntity entity);

	@Override
	BookmarkEntity toEntity(BookmarkDocument document);

	@Override
	List<BookmarkDocument> getDocumentList(List<BookmarkEntity> entityList);

	@Override
	List<BookmarkEntity> getEntityList(List<BookmarkDocument> documentList);
}
