import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.13"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.13.0"
}

group = "com.sc7258"
version = "0.0.1-SNAPSHOT"

//// --- 추가할 부분 ---
//// Spring Boot 플러그인에 메인 클래스를 명시적으로 지정하여 모호성을 해결합니다.
//springBoot {
//    mainClass.set("com.sc7258.apimulti.ApiMultiApplication")
//}
//// --------------------

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 기본 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Kotlin 관련 의존성
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // OpenAPI Generator가 생성한 코드에서 사용하는 의존성
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")

    // --- Swagger UI (SpringDoc OpenAPI) 의존성 추가 ---
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0") // 최신 버전 확인 후 사용


    // jakarta.validation-api 는 spring-boot-starter-validation에 포함되어 있음
    // implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // 컴파일 시 IDE에서 DevTools API를 인식할 수 있도록
    compileOnly("org.springframework.boot:spring-boot-devtools")
    // 실제 실행 시점에는 runtimeOnly로 포함
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
}

// 각 API 명세에 대한 코드 생성 작업을 정의
val apiSourcesDir = "${layout.buildDirectory.get().asFile}/generated/openapi"


// 코드 생성 작업을 위한 공통 설정을 함수로 분리하여 중복을 제거합니다.
fun GenerateTask.configureApiGeneration(packageName: String) {
    generatorName.set("kotlin-spring")
    outputDir.set(apiSourcesDir)
    apiPackage.set("com.sc7258.apimulti.api.$packageName")
    modelPackage.set("com.sc7258.apimulti.model.$packageName")
    configOptions.set(
        mapOf(
            "basePath" to "/api/v1",
            "useSpringBoot3" to "true",
            "useJakartaEe" to "true",
//            "useCoroutines" to "false",
//            "interfaceOnly" to "false",
            "delegatePattern" to "true",
//            "beanValidation" to "true",
//            "openApiNullable" to "true",
//            "enumPropertyNaming" to "UPPERCASE",
//            "documentationProvider" to "none",
//            "annotationLibrary" to "swagger2",
            "exceptionHandler" to "false" // Disable generation of DefaultExceptionHandler
        )
    )
}




// TVRO API 생성
tasks.register<GenerateTask>("generateTvroApi") {
    configureApiGeneration("tvro")
    inputSpec.set("$projectDir/src/main/resources/openapi/api-tvro.yaml")
}

// L-Band API 생성 (파일명 및 패키지명 수정 반영)
tasks.register<GenerateTask>("generateLbandApi") {
    configureApiGeneration("lband")
    inputSpec.set("$projectDir/src/main/resources/openapi/api-lband.yaml")
}

// Intellian API 생성
tasks.register<GenerateTask>("generateIntellianApi") {
    configureApiGeneration("intellian")
    inputSpec.set("$projectDir/src/main/resources/openapi/api-intellian.yaml")
}

// --- 컴파일 및 빌드 설정 ---

// 생성된 소스 코드를 메인 소스셋에 포함합니다.
sourceSets["main"].kotlin.srcDir(apiSourcesDir)




// compileKotlin 태스크가 generateApis 의존하도록 설정
tasks.named("compileKotlin") {
    dependsOn("generateApis")
}

// generateApis 태스크 정의
tasks.register("generateApis") {
    // generate* 태스크들에 의존
    dependsOn("generateTvroApi", "generateLbandApi", "generateIntellianApi")

    doLast {
        val dir = layout.buildDirectory.dir("generated/openapi/src/main/kotlin").get().asFile
        println("Checking directory: $dir")
        val files = fileTree(mapOf(
            "dir" to dir,
            "include" to listOf("**/Application.kt")
        )).files
        if (files.isNotEmpty()) {
            //println("Found Application.kt files: $files")
            delete(files)
            //println("Deleted Application.kt files in $dir")
        } else {
            println("No Application.kt files found in $dir")
        }
    }
}

//// 코틀린 컴파일 작업이 코드 생성 작업 이후에 실행되도록 의존성을 설정합니다.
//tasks.named("compileKotlin") {
//    // dependsOn(tasks.withType<GenerateTask>()) // <-- Replace this line
//    dependsOn("generateTvroApi", "generateLbandApi", "generateIntellianApi") // <-- With this line
//
//}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
