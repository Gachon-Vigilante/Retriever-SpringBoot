package com.vigilante.retriever.v1.user.application.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.common.domain.exception.BadRequestException;
import com.vigilante.retriever.common.domain.exception.BaseException;
import com.vigilante.retriever.common.domain.exception.UnauthorizedException;
import com.vigilante.retriever.v1.user.application.query.RefreshTokenRedisQuery;
import com.vigilante.retriever.v1.user.domain.code.TokenErrorCode;
import com.vigilante.retriever.v1.user.domain.entity.RefreshTokenEntity;
import com.vigilante.retriever.v1.user.domain.port.in.ReissueTokenUseCase;
import com.vigilante.retriever.v1.user.domain.port.out.AuthenticationPort;
import com.vigilante.retriever.v1.user.domain.port.out.TokenProviderPort;
import com.vigilante.retriever.v1.user.domain.vo.TokenResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueTokenService implements ReissueTokenUseCase {

	private final RefreshTokenRedisQuery refreshTokenRedisQuery;
	private final TokenProviderPort tokenProviderPort;
	private final AuthenticationPort authenticationPort;

	@Override
	@Transactional
	public TokenResult reissueToken(String refreshToken) {
		validateRefreshToken(refreshToken);

		String userId = tokenProviderPort.getUserIdFromJwt(refreshToken);
		verifyUserIdWithStoredToken(refreshToken, userId);

		Role role = tokenProviderPort.getRoleFromJwt(refreshToken);
		Collection<GrantedAuthority> authorities = List.of(role.toGrantedAuthority());

		UsernamePasswordAuthenticationToken authenticationToken = authenticationPort.createAuthentication(
			userId, role, authorities);
		log.info("새로운 access token을 생성하였습니다. userId: {}, role: {}, authorities: {}",
			userId, role.getRoleName(), authorities);

		return TokenResult.of(
			tokenProviderPort.issueAccessToken(authenticationToken),
			tokenProviderPort.issueRefreshToken(authenticationToken)
		);
	}

	private void validateRefreshToken(String refreshToken) {
		String validationResult = tokenProviderPort.validateToken(refreshToken);

		if (!"VALID_JWT".equals(validationResult)) {
			throw switch (validationResult) {
				case "EXPIRED_JWT_TOKEN" -> new UnauthorizedException(TokenErrorCode.REFRESH_TOKEN_EXPIRED_ERROR);
				case "INVALID_JWT_TOKEN" -> new BadRequestException(TokenErrorCode.INVALID_REFRESH_TOKEN_ERROR);
				case "INVALID_JWT_SIGNATURE" -> new BadRequestException(TokenErrorCode.REFRESH_TOKEN_SIGNATURE_ERROR);
				case "UNSUPPORTED_JWT_TOKEN" -> new BadRequestException(TokenErrorCode.UNSUPPORTED_REFRESH_TOKEN_ERROR);
				case "EMPTY_JWT" -> new BadRequestException(TokenErrorCode.REFRESH_TOKEN_EMPTY_ERROR);
				default -> new BaseException(TokenErrorCode.UNKNOWN_REFRESH_TOKEN_ERROR);
			};
		}
	}

	private void verifyUserIdWithStoredToken(String refreshToken, String userId) {
		RefreshTokenEntity storedToken = refreshTokenRedisQuery.findByTokenValue(refreshToken);

		if (!userId.equals(storedToken.userId())) {
			throw new BadRequestException(TokenErrorCode.REFRESH_TOKEN_USER_ID_MISMATCH_ERROR);
		}
	}
}

