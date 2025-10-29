package com.vigilante.retriever.v1.post.domain.dto.command;

import lombok.Builder;

@Builder
public record CreatePromotionRelationCommand(
	Long id,
	String postId
) {
}
