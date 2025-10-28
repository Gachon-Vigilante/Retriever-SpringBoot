package com.vigilante.retriever.infrastructure.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.convert.Neo4jConversions;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.lang.Nullable;

@Configuration
@EnableNeo4jRepositories(basePackages = {"com.vigilante.retriever.v1.*.adapter.out.persistence.neo4j.repository"})
public class Neo4jConfig {

	private final Driver driver;

	public Neo4jConfig(Driver driver) {
		this.driver = driver;
	}

	@Bean
	public Neo4jClient neo4jClient() {
		return Neo4jClient.create(driver);
	}

	@Bean
	public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient) {
		return new Neo4jTemplate(neo4jClient);
	}

	@Bean
	public Neo4jConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		// 드라이버의 Value 타입을 우선 처리하는 컨버터를 추가합니다.
		converters.add(new DriverValueToLocalDateTimeConverter());
		converters.add(new NumberToLocalDateTimeConverter());
		converters.add(new StringToLocalDateTimeConverter());
		return new Neo4jConversions(converters);
	}

	static class DriverValueToLocalDateTimeConverter implements Converter<Value, LocalDateTime> {
		@Override
		public @Nullable LocalDateTime convert(@Nullable Value source) {
			if (source == null || source.isNull())
				return null;
			// 먼저 네이티브 변환 시도 (Value가 temporal을 담고 있을 경우)
			try {
				return source.asLocalDateTime();
			} catch (Exception ignored) {
			}
			// Value 내부 객체를 꺼내서 Number/String으로 처리
			Object obj = source.asObject();
			if (obj instanceof Number n) {
				long value = n.longValue();
				if (Math.abs(value) > 1_000_000_000_000L) {
					return Instant.ofEpochMilli(value).atOffset(ZoneOffset.UTC).toLocalDateTime();
				}
				// 부동소수점 epoch 초 처리
				double dv = n.doubleValue();
				if (Math.abs(dv) > 1e12) {
					return Instant.ofEpochMilli((long)dv).atOffset(ZoneOffset.UTC).toLocalDateTime();
				}
				return Instant.ofEpochSecond(value).atOffset(ZoneOffset.UTC).toLocalDateTime();
			}
			if (obj instanceof String s) {
				try {
					return LocalDateTime.parse(s);
				} catch (Exception e) {
					try {
						long v = Long.parseLong(s);
						if (Math.abs(v) > 1_000_000_000_000L) {
							return Instant.ofEpochMilli(v).atOffset(ZoneOffset.UTC).toLocalDateTime();
						}
						return Instant.ofEpochSecond(v).atOffset(ZoneOffset.UTC).toLocalDateTime();
					} catch (Exception ex) {
						return null;
					}
				}
			}
			return null;
		}
	}

	static class NumberToLocalDateTimeConverter implements Converter<Number, LocalDateTime> {
		@Override
		public @Nullable LocalDateTime convert(@Nullable Number source) {
			if (source == null) {
				return null;
			}
			// Neo4j는 epoch 초 또는 밀리초를 숫자로 저장할 수 있습니다. 휴리스틱:
			// 값의 크기가 1e12보다 크면 밀리초로 간주하고, 그렇지 않으면 초로 간주합니다.
			long value = source.longValue();
			if (Math.abs(value) > 1_000_000_000_000L) { // > ~ 2286년 11월 20일 (참고)
				return Instant.ofEpochMilli(value).atOffset(ZoneOffset.UTC).toLocalDateTime();
			}
			// 부동소수점 형태의 epoch 초 처리(예: 1.759958362938E9)
			if (Math.abs(source.doubleValue()) > 1e12) {
				return Instant.ofEpochMilli((long)source.doubleValue()).atOffset(ZoneOffset.UTC).toLocalDateTime();
			}
			// epoch 초로 처리
			return Instant.ofEpochSecond(value).atOffset(ZoneOffset.UTC).toLocalDateTime();
		}
	}

	static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
		@Override
		public @Nullable LocalDateTime convert(@Nullable String source) {
			if (source == null) {
				return null;
			}
			// 먼저 ISO 포맷으로 파싱 시도
			try {
				return LocalDateTime.parse(source);
			} catch (Exception e) {
				// 대체: 숫자로 파싱 시도
				try {
					long v = Long.parseLong(source);
					if (Math.abs(v) > 1_000_000_000_000L) {
						return Instant.ofEpochMilli(v).atOffset(ZoneOffset.UTC).toLocalDateTime();
					}
					return Instant.ofEpochSecond(v).atOffset(ZoneOffset.UTC).toLocalDateTime();
				} catch (Exception ex) {
					return null;
				}
			}
		}
	}
}
