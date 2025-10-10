package com.vigilante.retriever.infrastructure.auth.jwt.provider;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.common.domain.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private static final String USER_ID = "userId";
	private static final String ROLE_KEY = "role";

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.access-token-expire-time}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token-expire-time}")
	private long refreshTokenExpireTime;

	@PostConstruct
	protected void init() {
		jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String issueAccessToken(final Authentication authentication) {
		return issueToken(authentication, accessTokenExpireTime);
	}

	public String issueRefreshToken(final Authentication authentication) {
		return issueToken(authentication, refreshTokenExpireTime);
	}

	public JwtValidationType validateToken(String token) {
		try {
			Claims claims = getBody(token);
			return JwtValidationType.VALID_JWT;
		} catch (MalformedJwtException ex) {
			log.error("올바르지 않은 JWT 토큰입니다: {}", ex.getMessage());
			return JwtValidationType.INVALID_JWT_TOKEN;
		} catch (ExpiredJwtException ex) {
			log.error("만료된 JWT 토큰입니다: {}", ex.getMessage());
			return JwtValidationType.EXPIRED_JWT_TOKEN;
		} catch (UnsupportedJwtException ex) {
			log.error("지원하지 않는 JWT 토큰입니다: {}", ex.getMessage());
			return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
		} catch (IllegalArgumentException ex) {
			log.error("JWT 토큰이 비어있거나 올바르지 않은 인자입니다: {}", ex.getMessage());
			return JwtValidationType.EMPTY_JWT;
		} catch (SignatureException ex) {
			log.error("올바르지 않은 JWT Signature 입니다: {}", ex.getMessage());
			return JwtValidationType.INVALID_JWT_SIGNATURE;
		}
	}

	public String getUserIdFromJwt(String token) {
		Claims claims = getBody(token);
		return claims.get(USER_ID, String.class);
	}

	public Role getRoleFromJwt(String token) {
		Claims claims = getBody(token);
		String roleName = claims.get(ROLE_KEY, String.class);
		return Role.valueOf(roleName.replace("ROLE_", ""));
	}

	private String issueToken(final Authentication authentication, final long expiredTime) {
		final Date now = new Date();

		String role = authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No authorities found for user"));

		return Jwts.builder()
			.header().type("JWT")
			.and()
			.issuedAt(now)
			.expiration(new Date(now.getTime() + expiredTime))
			.claim(USER_ID, authentication.getPrincipal())
			.claim(ROLE_KEY, role)
			.signWith(getSigningKey())
			.compact();
	}

	private Claims getBody(final String token) {
		return Jwts.parser()
			.verifyWith(getSigningKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	private SecretKey getSigningKey() {
		String encodedKey = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
		return Keys.hmacShaKeyFor(encodedKey.getBytes());
	}
}

