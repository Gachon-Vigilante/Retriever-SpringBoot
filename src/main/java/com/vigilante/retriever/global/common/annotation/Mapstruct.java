package com.vigilante.retriever.global.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Primary
@org.mapstruct.Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public @interface Mapstruct {
}
