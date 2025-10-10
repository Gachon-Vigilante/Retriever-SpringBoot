package com.vigilante.retriever.infrastructure.auth.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) {
		String path = request.getRequestURI();
		String method = request.getMethod();
		log.warn("접근 거부: Method: {}, Path: {}, Message: {}", method, path, accessDeniedException.getMessage());

		setResponse(response);
	}

	private void setResponse(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
}
