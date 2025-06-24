package com.sc7258.batchmultidb.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.batch.BatchDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
@EnableConfigurationProperties(BatchDataSourceProperties::class)
class BatchInfraConfig(
    private val props: BatchDataSourceProperties
) {

    @Bean
    @BatchDataSource
    fun batchDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(props.url)
            .username(props.username)
            .password(props.password)
            .driverClassName(props.driverClassName)
            .build()
    }

    @Bean
    @Qualifier("batchTransactionManager")
    fun batchTransactionManager(@Qualifier("batchDataSource") dataSource: DataSource): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    // Using Spring Boot's auto-configured jobRepository and jobLauncher
}
