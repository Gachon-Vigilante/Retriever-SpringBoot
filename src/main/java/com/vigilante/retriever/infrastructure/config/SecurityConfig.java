package com.vigilante.retriever.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vigilante.retriever.common.domain.enums.Role;
import com.vigilante.retriever.infrastructure.auth.jwt.filter.JwtAuthenticationFilter;
import com.vigilante.retriever.infrastructure.auth.security.CustomAccessDeniedHandler;
import com.vigilante.retriever.infrastructure.auth.security.CustomJwtAuthenticationEntryPoint;
import com.vigilante.retriever.infrastructure.auth.security.SecurityEndpointRegistry;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final SecurityEndpointRegistry endpointRegistry;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(exception ->
				exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint)
					.accessDeniedHandler(customAccessDeniedHandler));

		http.authorizeHttpRequests(auth ->
				auth.requestMatchers(HttpMethod.OPTIONS, "/**")
					.permitAll()
					.requestMatchers(endpointRegistry.whitelist())
					.permitAll()
					.requestMatchers(endpointRegistry.adminOrRootOnly())
					.hasAnyAuthority(Role.ADMIN.getRoleName(), Role.ROOT.getRoleName())
					.requestMatchers(endpointRegistry.rootOnly())
					.hasAuthority(Role.ROOT.getRoleName())
					.anyRequest()
					.authenticated()
			)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
