package com.sc7258.apiversioning.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * springdoc-openapi 라이브러리의 동작을 설정하는 클래스입니다.
 * 여기서는 API를 버전별로 그룹화하여 Swagger UI에서 쉽게 탐색할 수 있도록 합니다.
 */
@Configuration
class OpenApiConfig {

    /**
     * API 문서의 전역 정보를 설정합니다.
     * Swagger UI의 최상단에 표시될 제목, 설명, 버전 등을 정의합니다.
     * 이 정보는 v1, v2 그룹에 모두 공통으로 적용됩니다.
     */
    @Bean
    fun globalOpenApiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("API Versioning Example")
                    .description("API 버전 관리를 위한 Spring Boot 프로젝트 문서입니다.")
                    .version("1.0.0")
            )
    }

    /**
     * v1 API를 위한 그룹을 정의합니다.
     * SpringDoc이 자동으로 찾아낸 API 중 경로가 /api/v1/으로 시작하는 모든 것을
     * Swagger UI의 'api-v1' 그룹으로 묶어줍니다.
     */
    @Bean
    fun openApiGroupV1(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("api-v1")
            .pathsToMatch("/api/v1/**")
            .build()
    }

    /**
     * v2 API를 위한 그룹을 정의합니다.
     * SpringDoc이 자동으로 찾아낸 API 중 경로가 /api/v2/으로 시작하는 모든 것을
     * Swagger UI의 'api-v2' 그룹으로 묶어줍니다.
     */
    @Bean
    fun openApiGroupV2(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("api-v2")
            .pathsToMatch("/api/v2/**")
            .build()
    }
}