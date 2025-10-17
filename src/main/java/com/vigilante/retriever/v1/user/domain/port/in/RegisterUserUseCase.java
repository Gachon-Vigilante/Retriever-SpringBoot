package com.vigilante.retriever.v1.user.domain.port.in;

import com.vigilante.retriever.v1.user.domain.dto.command.RegisterUserCommand;

public interface RegisterUserUseCase {

	void signUp(RegisterUserCommand command);
}
