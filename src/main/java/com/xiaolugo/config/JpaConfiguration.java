package com.xiaolugo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by chinaD on 2017/11/28.
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean
})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.xiaolugo.repository")
@EntityScan(basePackages = "com.xiaolugo.entity")
public class JpaConfiguration {

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return  new PersistenceExceptionTranslationPostProcessor();
    }
}
