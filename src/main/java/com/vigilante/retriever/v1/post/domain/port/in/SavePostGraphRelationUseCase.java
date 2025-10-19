package com.vigilante.retriever.v1.post.domain.port.in;

import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;

public interface SavePostGraphRelationUseCase {

	void createPromotionRelation(CreatePromotionRelationCommand command);
}
