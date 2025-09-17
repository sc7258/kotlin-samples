
import org.jetbrains.kotlin.gradle.tasks.KaptGenerateStubs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    id("org.openapi.generator") version "7.5.0"
}

group = "com.sc7258"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Dependencies for OpenAPI Generator
    implementation("jakarta.validation:jakarta.validation-api") // Use jakarta namespace for Spring Boot 3
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.13.10") // Add MockK dependency
}

kapt {
    correctErrorTypes = true
}

val openApiGenerate by tasks.getting(GenerateTask::class) {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/api/openapi.yaml")
    outputDir.set("${buildDir}/generated/sources/openapi/kotlin")
    apiPackage.set("com.sc7258.simpleorderhexagonal.generated.api")
    modelPackage.set("com.sc7258.simpleorderhexagonal.generated.model")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "useTags" to "true",
            "serializableModel" to "true",
            "documentationProvider" to "none",
            "annotationLibrary" to "swagger2"
        )
    )
}

sourceSets.main {
    kotlin.srcDir("${buildDir}/generated/sources/openapi/kotlin/src/main/kotlin")
}

tasks.withType<KaptGenerateStubs> {
    dependsOn(openApiGenerate)
}

tasks.withType<KotlinCompile> {
    dependsOn(openApiGenerate)
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
