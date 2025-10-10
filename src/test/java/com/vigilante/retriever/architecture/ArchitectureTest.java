package com.vigilante.retriever.architecture;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * 아키텍처 검증 테스트
 *
 * 핵심 원칙:
 *   - Domain: 순수 비즈니스 로직, 어떤 계층에도 의존하지 않음
 *   - Application: 유스케이스, Domain과 Port만 의존
 *   - Adapter: Port 구현, 외부 기술과 Domain 연결
 *   - Infrastructure: 기술 구현 세부사항 (설정, 공통 유틸)
 */
@AnalyzeClasses(packages = "com.vigilante.retriever")
public class ArchitectureTest {

	/**
	 * 1. Domain Layer 규칙
	 */
	@ArchTest
	static final ArchRule domain_should_not_depend_on_adapters =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..v1..adapter..")
			.because("Domain은 Adapter에 의존해서는 안 됩니다 (헥사고날 핵심 규칙)");

	@ArchTest
	static final ArchRule domain_should_not_depend_on_infrastructure =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..infrastructure..")
			.because("Domain은 Infrastructure에 의존해서는 안 됩니다 (헥사고날 핵심 규칙)");

	@ArchTest
	static final ArchRule domain_should_not_depend_on_application =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..v1..application..")
			.because("Domain은 Application에 의존해서는 안 됩니다");

	@ArchTest
	static final ArchRule domain_entities_should_not_use_infrastructure_annotations =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..domain.entity..")
			.should().dependOnClassesThat()
			.resideInAnyPackage(
				"org.springframework.data.mongodb..",
				"org.springframework.data.redis..",
				"org.springframework.data.neo4j.."
			)
			.because("Domain Entity는 순수 Java 객체여야 합니다");

	/**
	 * 2. Application Layer 규칙
	 */
	@ArchTest
	static final ArchRule application_should_not_depend_on_adapters =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..application..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..v1..adapter..")
			.because("Application은 Adapter에 의존하지 않고 Port를 통해 연결됩니다");

	@ArchTest
	static final ArchRule application_should_not_depend_on_infrastructure =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..application..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..infrastructure..")
			.because("Application은 Infrastructure에 의존해서는 안 됩니다");

	@ArchTest
	static final ArchRule application_services_should_use_service_annotation =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..v1..application..")
			.and().haveSimpleNameEndingWith("Command")
			.or().haveSimpleNameEndingWith("Query")
			.should().beAnnotatedWith("com.vigilante.retriever.common.domain.annotation.CommandService")
			.orShould().beAnnotatedWith("com.vigilante.retriever.common.domain.annotation.QueryService")
			.allowEmptyShould(true)
			.because("Application Service는 @CommandService 또는 @QueryService를 사용해야 합니다");

	/**
	 * 3. Adapter Layer 규칙
	 */
	@ArchTest
	static final ArchRule adapter_out_should_not_depend_on_application =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..v1..adapter.out..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..v1..application..")
			.because("Adapter.Out은 Application이 아닌 Port만 의존해야 합니다");

	@ArchTest
	static final ArchRule adapter_implementations_should_be_components =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..adapter.out..adapter")
			.and().haveSimpleNameEndingWith("Adapter")
			.should().beAnnotatedWith(Component.class)
			.allowEmptyShould(true)
			.because("Adapter 구현체는 @Component로 등록되어야 합니다");

	@ArchTest
	static final ArchRule adapter_in_should_not_depend_on_domain_entity =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..adapter.in..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..domain.entity..")
			.allowEmptyShould(true)
			.because("Adapter.In은 Domain Entity가 아닌 DTO를 사용해야 합니다");

	/**
	 * 4. Common Domain Layer 규칙
	 */
	@ArchTest
	static final ArchRule common_domain_should_not_depend_on_infrastructure =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..common.domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..infrastructure..")
			.because("Common Domain은 Infrastructure에 의존해서는 안 됩니다");

	@ArchTest
	static final ArchRule common_domain_should_not_depend_on_adapter =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..common.domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..adapter..")
			.because("Common Domain은 Adapter에 의존해서는 안 됩니다");

	@ArchTest
	static final ArchRule common_domain_should_not_depend_on_v1_domains =
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..common.domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..v1..")
			.because("Common Domain은 특정 비즈니스 도메인에 의존해서는 안 됩니다");

	/**
	 * 5. Port 규칙
	 */
	@ArchTest
	static final ArchRule ports_should_be_interfaces =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.port..")
			.should().beInterfaces()
			.because("Port는 인터페이스여야 합니다");

	@ArchTest
	static final ArchRule inbound_ports_naming_convention =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.port.in")
			.should().haveSimpleNameEndingWith("UseCase")
			.allowEmptyShould(true)
			.because("Inbound Port는 UseCase로 끝나야 합니다");

	@ArchTest
	static final ArchRule outbound_ports_naming_convention =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.port.out")
			.should().haveSimpleNameEndingWith("Port")
			.allowEmptyShould(true)
			.because("Outbound Port는 Port로 끝나야 합니다");

	/**
	 * 6. Exception 규칙
	 */
	@ArchTest
	static final ArchRule domain_exceptions_should_extend_base_exception =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..v1..domain.exception")
			.and().haveSimpleNameEndingWith("Exception")
			.should().beAssignableTo("com.vigilante.retriever.common.domain.exception.BaseException")
			.allowEmptyShould(true)
			.because("Domain 예외는 BaseException을 상속해야 합니다");

	@ArchTest
	static final ArchRule error_codes_should_implement_base_code =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.code")
			.and().haveSimpleNameEndingWith("ErrorCode")
			.should().implement("com.vigilante.retriever.common.domain.code.BaseCode")
			.allowEmptyShould(true)
			.because("ErrorCode는 BaseCode를 구현해야 합니다");

	/**
	 * 7. Controller 규칙
	 */
	@ArchTest
	static final ArchRule rest_controllers_should_reside_in_adapter_in_web =
		ArchRuleDefinition.classes()
			.that().areAnnotatedWith(RestController.class)
			.should().resideInAPackage("..adapter.in.web..")
			.allowEmptyShould(true)
			.because("RestController는 adapter.in.web 패키지에 있어야 합니다");

	/**
	 * 8. Value Object (VO) 규칙
	 */
	@ArchTest
	static final ArchRule value_objects_should_be_records =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.vo..")
			.and().haveSimpleNameNotContaining("Builder")
			.and().areNotEnums()
			.should().beRecords()
			.allowEmptyShould(true)
			.because("Value Object는 불변 record로 구현되어야 합니다");

	/**
	 * 9. Naming Convention 규칙
	 */
	@ArchTest
	static final ArchRule entities_should_end_with_entity =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..domain.entity")
			.and().areNotMemberClasses()
			.should().haveSimpleNameEndingWith("Entity")
			.allowEmptyShould(true)
			.because("Domain Entity는 Entity로 끝나야 합니다");

	@ArchTest
	static final ArchRule adapters_should_end_with_adapter =
		ArchRuleDefinition.classes()
			.that().resideInAPackage("..adapter.out..adapter")
			.and().areNotMemberClasses()
			.should().haveSimpleNameEndingWith("Adapter")
			.allowEmptyShould(true)
			.because("Adapter 구현체는 Adapter로 끝나야 합니다");
}
