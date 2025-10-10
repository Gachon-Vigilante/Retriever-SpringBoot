package com.vigilante.retriever.infrastructure.common.mapper;

import java.util.List;

public interface GenericRedisMapper<H, E> {

	// Hash를 Entity로 변환
	H toHash(E entity);

	// Hash를 Entity로 변환
	E toEntity(H hash);

	// Entity 목록을 Hash 목록으로 변환
	List<H> getHashList(List<E> entityList);

	// Hash 목록을 Entity 목록으로 변환
	List<E> getEntityList(List<H> hashList);
}
