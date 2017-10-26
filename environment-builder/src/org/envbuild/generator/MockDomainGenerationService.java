package org.envbuild.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kovlyashenko
 */
@Component
@Services.DomainService
public class MockDomainGenerationService extends GenerationService {

    @Override
    @Autowired
    @DomainGenerator
    public void setGenerator(RandomGenerator generator) {
        super.setGenerator(generator);
        
    }

    
    
}
