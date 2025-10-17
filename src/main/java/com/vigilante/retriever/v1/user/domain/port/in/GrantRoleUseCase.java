package com.vigilante.retriever.v1.user.domain.port.in;

import com.vigilante.retriever.v1.user.domain.dto.command.GrantRoleCommand;

public interface GrantRoleUseCase {

	void grantRole(GrantRoleCommand command);
}
