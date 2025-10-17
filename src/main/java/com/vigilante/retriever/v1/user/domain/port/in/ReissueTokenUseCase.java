package com.vigilante.retriever.v1.user.domain.port.in;

import com.vigilante.retriever.v1.user.domain.vo.TokenResult;

public interface ReissueTokenUseCase {

	TokenResult reissueToken(String refreshToken);
}

