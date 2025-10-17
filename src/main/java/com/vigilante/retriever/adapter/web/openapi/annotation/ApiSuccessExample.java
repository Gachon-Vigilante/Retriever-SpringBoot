package com.vigilante.retriever.adapter.web.openapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiSuccessExample {
	Success[] value();

	boolean examples() default true;

	boolean overrideExisting() default false;

	@interface Success {
		String code();

		String exampleKey() default "";

		String mediaType() default "application/json";

		String description() default "";

		String schemaRef() default "";
	}
}
