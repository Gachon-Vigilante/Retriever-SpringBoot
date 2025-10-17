package com.vigilante.retriever.v1.user.adapter.in.web.util;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.common.domain.exception.UnauthorizedException;
import com.vigilante.retriever.v1.user.domain.code.TokenErrorCode;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenExtractor {

	public String extractRefreshToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			throw new UnauthorizedException(TokenErrorCode.EMPTY_OR_INVALID_TOKEN);
		}

		return Arrays.stream(cookies)
			.filter(cookie -> "refreshToken".equals(cookie.getName()))
			.map(Cookie::getValue)
			.findFirst()
			.orElseThrow(() -> new UnauthorizedException(TokenErrorCode.EMPTY_OR_INVALID_TOKEN));
	}
}

