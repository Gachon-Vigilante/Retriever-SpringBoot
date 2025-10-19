package com.vigilante.retriever.v1.report.adapter.in.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.report.adapter.in.web.dto.ReportInfoResponse;
import com.vigilante.retriever.v1.report.domain.entity.ReportEntity;

@Component
public class ReportWebMapper {

	public ReportInfoResponse toResponse(ReportEntity entity) {
		return ReportInfoResponse.builder()
			.id(entity.id())
			.channelId(entity.channelId())
			.chatId(entity.chatId())
			.type(entity.type())
			.content(entity.content())
			.description(entity.description())
			.timestamp(entity.timestamp())
			.build();
	}

	public List<ReportInfoResponse> toResponseList(List<ReportEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}
}
