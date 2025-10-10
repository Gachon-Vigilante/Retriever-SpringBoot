package com.vigilante.retriever.v1.user.adapter.out.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;

import com.vigilante.retriever.infrastructure.common.mapper.GenericRedisMapper;
import com.vigilante.retriever.v1.user.adapter.out.persistence.redis.hash.RefreshTokenHash;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;

@Primary
@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface RefreshTokenRedisMapper extends GenericRedisMapper<RefreshTokenHash, RefreshTokenEntity> {

	@Override
	RefreshTokenHash toHash(RefreshTokenEntity entity);

	@Override
	RefreshTokenEntity toEntity(RefreshTokenHash hash);

	@Override
	List<RefreshTokenHash> getHashList(List<RefreshTokenEntity> entityList);

	@Override
	List<RefreshTokenEntity> getEntityList(List<RefreshTokenHash> hashList);
}
