package com.sc7258.batchmultidb.config
// import com.sc7258.batchmultidb.entity.SampleEntity // 사용하지 않는다면 제거 가능 // 이 줄은 주석 처리하거나 삭제해도 됩니다.

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.sc7258.batchmultidb.repository"],
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appJpaTransactionManager"
)
class AppDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun appDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    fun appDataSource(appDataSourceProperties: DataSourceProperties): DataSource {
        return appDataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean // Hibernate 특정 속성을 위한 빈 추가
    @ConfigurationProperties("spring.jpa.hibernate")
    fun hibernateProperties(): HibernateProperties {
        return HibernateProperties()
    }


    @Primary
    @Bean
    fun appEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("appDataSource") appDataSource: DataSource,
        jpaProperties: JpaProperties, // 일반 JPA 속성 주입
        hibernateProperties: HibernateProperties // HibernateProperties 주입
    ): LocalContainerEntityManagerFactoryBean {
        // HibernateProperties에서 ddl-auto 값 직접 가져오기
        val ddlAutoValue = hibernateProperties.ddlAuto

        println("[DEBUG] appEntityManagerFactory - JpaProperties raw map: ${jpaProperties.properties}")
        println("[DEBUG] appEntityManagerFactory - HibernateProperties ddl-auto: $ddlAutoValue")

        // JpaProperties의 전체 속성으로 초기화
        val effectiveProps = HashMap(jpaProperties.properties)

        // HibernateProperties에서 가져온 ddl-auto 값으로 명시적 설정/덮어쓰기
        // 이는 jpaProperties.hibernate 접근 문제를 우회하기 위함입니다.
        if (!ddlAutoValue.isNullOrBlank()) {
            effectiveProps["hibernate.hbm2ddl.auto"] = ddlAutoValue
        }
        // 만약 hibernateProperties.ddlAuto가 비어있다면,
        // effectiveProps에는 jpaProperties.properties에 이미 설정된 "hibernate.hbm2ddl.auto" 값이 사용됩니다.

        println("[DEBUG] appEntityManagerFactory - Effective Props for EMF: $effectiveProps")

        return builder
            .dataSource(appDataSource)
            .packages("com.sc7258.batchmultidb.entity")
            .properties(effectiveProps) // 최종적으로 구성된 속성 맵 사용
            .persistenceUnit("app")
            .build()
    }

    @Primary
    @Bean
    fun appJpaTransactionManager(
        @Qualifier("appEntityManagerFactory") appEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(appEntityManagerFactory.`object`!!)
    }
}