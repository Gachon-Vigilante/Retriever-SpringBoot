package com.vigilante.retriever.global.common.mapper;

import java.util.List;

public interface GenericMongoMapper<D, E> {

	// Document를 Entity로 변환
	D toDocument(E entity);

	// Document를 Entity로 변환
	E toEntity(D document);

	// Entity 목록을 Document 목록으로 변환
	List<D> getDocumentList(List<E> entityList);

	// Document 목록을 Entity 목록으로 변환
	List<E> getEntityList(List<D> documentList);
}
