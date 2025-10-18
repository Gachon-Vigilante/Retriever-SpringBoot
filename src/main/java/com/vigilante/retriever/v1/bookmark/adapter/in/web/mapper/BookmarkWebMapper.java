package com.vigilante.retriever.v1.bookmark.adapter.in.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.bookmark.adapter.in.web.dto.response.BookmarkInfoResponse;
import com.vigilante.retriever.v1.bookmark.domain.entity.BookmarkEntity;

@Component
public class BookmarkWebMapper {

	public BookmarkInfoResponse toInfoResponse(BookmarkEntity entity) {
		return BookmarkInfoResponse.builder()
			.id(entity.id())
			.channelId(entity.channelId())
			.userId(entity.userId())
			.createdAt(entity.createdAt())
			.updatedAt(entity.updatedAt())
			.build();
	}

	public List<BookmarkInfoResponse> toInfoResponseList(List<BookmarkEntity> entities) {
		return entities.stream()
			.map(this::toInfoResponse)
			.collect(Collectors.toList());
	}
}
