package com.vigilante.retriever.adapter.web.openapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiErrorExample {
	String[] include() default {};

	boolean examples() default true;

	boolean overrideExisting() default false;

	ErrorSpec[] custom() default {};

	@interface ErrorSpec {
		String code();

		String exampleKey() default "";

		String description() default "";

		String mediaType() default "application/json";

		String schemaRef() default "";

		boolean override() default true;
	}
}
