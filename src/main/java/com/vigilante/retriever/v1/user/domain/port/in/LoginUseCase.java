package com.vigilante.retriever.v1.user.domain.port.in;

import com.vigilante.retriever.v1.user.domain.dto.command.LoginCommand;
import com.vigilante.retriever.v1.user.domain.vo.LoginResult;

public interface LoginUseCase {

	LoginResult login(LoginCommand command);
}
