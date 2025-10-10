package com.vigilante.retriever.infrastructure.auth.jwt.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.infrastructure.auth.jwt.provider.JwtTokenProvider;
import com.vigilante.retriever.infrastructure.auth.jwt.provider.JwtValidationType;
import com.vigilante.retriever.infrastructure.auth.security.AdminAuthentication;
import com.vigilante.retriever.infrastructure.auth.security.MemberAuthentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {

		final String token = extractTokenFromRequest(request);

		if (!StringUtils.hasText(token)) {
			log.debug("쿠키에서 JWT 토큰을 찾을 수 없습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		try {
			JwtValidationType validationType = jwtTokenProvider.validateToken(token);

			if (validationType == JwtValidationType.VALID_JWT) {
				setAuthentication(token, request);
				filterChain.doFilter(request, response);
			} else {
				handleInvalidToken(validationType, response);
			}
		} catch (Exception e) {
			log.error("JWT Authentication Exception: ", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void setAuthentication(String token, HttpServletRequest request) {
		String userId = jwtTokenProvider.getUserIdFromJwt(token);
		Role role = jwtTokenProvider.getRoleFromJwt(token);

		log.debug("Setting authentication for userId: {} with role: {}", userId, role);

		Collection<GrantedAuthority> authorities = List.of(role.toGrantedAuthority());
		UsernamePasswordAuthenticationToken authentication = createAuthentication(userId, authorities, role);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UsernamePasswordAuthenticationToken createAuthentication(
		String userId,
		Collection<? extends GrantedAuthority> authorities,
		Role role) {

		if (role == Role.ADMIN || role == Role.ROOT) {
			return new AdminAuthentication(userId, null, authorities);
		} else if (role == Role.USER) {
			return new MemberAuthentication(userId, null, authorities);
		}

		throw new IllegalArgumentException("Unknown role: " + role);
	}

	private void handleInvalidToken(JwtValidationType validationType, HttpServletResponse response) {
		if (validationType == JwtValidationType.EXPIRED_JWT_TOKEN) {
			log.debug("JWT 토큰이 만료되었습니다.");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			log.debug("올바르지 않은 JWT 토큰입니다: {}", validationType);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private String extractTokenFromRequest(HttpServletRequest request) {
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				return null;
			}

			return Arrays.stream(cookies)
				.filter(cookie -> "accessToken".equals(cookie.getName()))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);
		} catch (Exception e) {
			log.warn("AccessToken 추출 실패: {}", e.getMessage());
			return null;
		}
	}
}
