package org.envbuild.spring.config;

import org.envbuild.environment.DbEnvironmentBuilder;
import org.envbuild.environment.EnvironmentPrototypeBuilder;
import org.envbuild.generator.GenerationService;
import org.envbuild.generator.MockDomainGenerator;
import org.envbuild.generator.RandomGenerator;
import org.envbuild.spring.environment.pattern.DbEnvironmentPatternRegistry;
import org.envbuild.spring.generator.DomainGenerator;
import org.envbuild.spring.generator.Services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Salamansar
 */
@Configuration
public class PersistOrientedGeneratorConfig {
    
    @Bean
    @DomainGenerator
    public MockDomainGenerator mockDomainGenerator() {
        return new MockDomainGenerator();
    }
    
    @Bean
    public DomainPostProcessorCollector domainPostProcessorCollector() {
        return new DomainPostProcessorCollector();
    }
    
    @Bean
    @Services.DomainService
    public GenerationService mockDomainGenerationService(@DomainGenerator RandomGenerator generator) {
        GenerationService service = new GenerationService();
        service.setGenerator(generator);
        return service;
    }
    
    @Bean
    public DbEnvironmentBuilder dbEnvironmentBuilder() {
        return new DbEnvironmentBuilderBean();
    }
    
    @Bean
    public EnvironmentPrototypeBuilder environmentPrototypeBuilder(DbEnvironmentBuilder envBuilder) {
        return new EnvironmentPrototypeBuilder(envBuilder);
    }
    
    @Bean
    public DbEnvironmentPatternRegistry dbEnvironmentPatternRegistry() {
        return new DbEnvironmentPatternRegistry();
    }
    
    
}
