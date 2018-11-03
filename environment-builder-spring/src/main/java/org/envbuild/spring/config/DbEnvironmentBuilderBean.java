package org.envbuild.spring.config;

import org.envbuild.environment.DbEnvironmentBuilder;
import org.envbuild.generator.RandomGenerator;
import org.envbuild.generator.processor.DomainPersister;
import org.envbuild.spring.generator.DomainGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Salamansar
 */
public class DbEnvironmentBuilderBean extends DbEnvironmentBuilder {

    @Override
    @Autowired(required = false)
    public void setDomainPersister(DomainPersister domainPersister) {
        super.setDomainPersister(domainPersister); 
    }

    @Override
    @Autowired
    public void setRandomGenerator(@DomainGenerator  RandomGenerator randomGenerator) {
        super.setRandomGenerator(randomGenerator); 
    }
    
}
