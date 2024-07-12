package com.example.config;

import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


@Configuration
public class MyBatisConfig {
 
    @Bean
    public org.apache.ibatis.session.Configuration myBatisConfiguration() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mappers/**/*.xml");

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

        for (Resource resource : resources) {
            try (InputStream inputStream = resource.getInputStream()) {
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource.getFilename(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            }
        }

        return configuration;
    }
}
