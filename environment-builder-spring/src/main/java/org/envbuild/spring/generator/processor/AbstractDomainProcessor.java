package org.envbuild.spring.generator.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.envbuild.spring.generator.DomainGenerator;
import org.envbuild.generator.RandomGenerator;
import org.envbuild.generator.processor.DomainPersister;
import org.envbuild.generator.processor.DomainPostProcessor;

/**
 * @author kovlyashenko
 */
public abstract class AbstractDomainProcessor<T> implements DomainPostProcessor<T> {
    private DomainPersister persister;  
    private RandomGenerator randomGenerator;

    public DomainPersister sessionFactory() {
        return persister;
    }

    @Autowired
    public void setSessionFactory(DomainPersister persister) {
        this.persister = persister;
    }

    public RandomGenerator randomGenerator() {
        return randomGenerator;
    }

    @Autowired
    @DomainGenerator
    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
    
}
