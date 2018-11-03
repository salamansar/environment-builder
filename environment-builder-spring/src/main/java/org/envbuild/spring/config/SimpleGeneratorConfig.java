package org.envbuild.spring.config;

import org.envbuild.generator.GenerationService;
import org.envbuild.generator.RandomGenerator;
import org.envbuild.spring.generator.Services;
import org.envbuild.spring.generator.SimpleGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Salamansar
 */
@Configuration
public class SimpleGeneratorConfig {
    
    @Bean
    @SimpleGenerator
    public RandomGenerator simpleRandomGenerator() {
        return new RandomGenerator();
    }
    
    @Bean
    @Services.SimpleService
    public GenerationService simpleGenerationService(@SimpleGenerator RandomGenerator generator) {
        GenerationService service = new GenerationService();
        service.setGenerator(generator);
        return service;
    }
    
}
