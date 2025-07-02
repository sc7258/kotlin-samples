import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask // GenerateTask 클래스를 사용합니다.

plugins {
    id("org.springframework.boot") version "3.3.13"
    id("io.spring.dependency-management") version "1.1.7"

    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"

    // JPA와 Kotlin 통합 플러그인 (선택 사항, 필요 시 추가)
    kotlin("plugin.jpa") version "1.9.20"
    // OpenAPI Generator Gradle 플러그인 적용
    id("org.openapi.generator") version "7.8.0" // 사용 중인 버전
}

group = "com.sc7258"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Web 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Spring Boot Validation 의존성
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // Kotlin 리플렉션 의존성
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // Kotlin 표준 라이브러리 의존성
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // OpenAPI Generator가 생성하는 코드에 필요한 의존성
    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // SpringDoc OpenAPI (Swagger UI) 의존성 추가
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Spring Boot 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
}

// Kotlin 컴파일러 설정
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

// OpenAPI Generator Gradle 플러그인 설정
tasks {
    // [개선 1] 생성된 소스가 저장될 디렉토리를 변수로 명확하게 정의합니다.
    val generatedSourcesDir = layout.buildDirectory.dir("generated/openapi")

    // v1과 v2 작업의 공통 설정을 변수로 추출하여 중복을 제거합니다.
    val commonApiConfigOptions = mapOf(
        "delegatePattern" to "true",
        "interfaceOnly" to "true",
        "dateLibrary" to "java8",
        "useJakartaEe" to "true",
        "useSpringBoot3" to "true",
        "library" to "spring-boot",
        "supportingFiles" to "false"
    )

    // v1 API 코드 생성 설정
    register<GenerateTask>("generateV1Api") {
        group = "openapi tools"
        description = "Generates v1 API source code from openapi yaml"

        inputSpec.set(layout.projectDirectory.file("src/main/resources/openapi/v1.yaml").asFile.path)
        generatorName.set("kotlin-spring")
        apiPackage.set("com.sc7258.apiversioning.generated.v1.api")
        modelPackage.set("com.sc7258.apiversioning.generated.v1.model")
        // [수정 1] outputDir을 중첩되지 않는 경로로 설정합니다.
        outputDir.set(generatedSourcesDir.get().asFile.path)
        configOptions.set(commonApiConfigOptions)
    }

    // v2 API 코드 생성 설정
    register<GenerateTask>("generateV2Api") {
        group = "openapi tools"
        description = "Generates v2 API source code from openapi yaml"

        inputSpec.set(layout.projectDirectory.file("src/main/resources/openapi/v2.yaml").asFile.path)
        generatorName.set("kotlin-spring")
        apiPackage.set("com.sc7258.apiversioning.generated.v2.api")
        modelPackage.set("com.sc7258.apiversioning.generated.v2.model")
        // [수정 1] outputDir을 중첩되지 않는 경로로 설정합니다.
        outputDir.set(generatedSourcesDir.get().asFile.path)
        configOptions.set(commonApiConfigOptions)
    }

    // compileKotlin 작업이 코드 생성 작업에 의존하도록 설정합니다.
    named("compileKotlin") {
        dependsOn(named("generateV1Api"), named("generateV2Api"))
    }

    // [개선 2] clean 작업 시 생성된 소스 디렉토리도 함께 삭제하여 빌드 안정성을 높입니다.
    named("clean", Delete::class) {
        delete(generatedSourcesDir)
    }
}


// Gradle 빌드 시 생성된 소스 코드가 컴파일되도록 설정
sourceSets["main"].apply {
    // [수정 2] java.srcDir 대신 kotlin.srcDir을 사용하고, 올바른 경로를 지정합니다.
    kotlin.srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    // Gradle 7.x 이상에서는 자동으로 포함되지만, 명시적으로 추가하여 확인
}