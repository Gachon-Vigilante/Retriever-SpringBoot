package com.vigilante.retriever.v1.bookmark.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.global.common.mapper.GenericMapper;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;
import com.vigilante.retriever.v1.bookmark.infrastructure.persistence.document.BookmarkDocument;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BookmarkPersistenceMapper extends GenericMapper<BookmarkDocument, BookmarkEntity> {

	@Override
	BookmarkDocument toDocument(BookmarkEntity entity);

	@Override
	BookmarkEntity toEntity(BookmarkDocument document);

	@Override
	List<BookmarkDocument> getDocumentList(List<BookmarkEntity> entityList);

	@Override
	List<BookmarkEntity> getEntityList(List<BookmarkDocument> dodumentList);
}
