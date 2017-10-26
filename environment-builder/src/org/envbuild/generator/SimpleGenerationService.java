package org.envbuild.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kovlyashenko
 */
@Component
@Services.SimpleService
public class SimpleGenerationService extends GenerationService {

    @Override
    @Autowired
    @SimpleGenerator
    public void setGenerator(RandomGenerator generator) {
        super.setGenerator(generator);
    }

    
    
}
