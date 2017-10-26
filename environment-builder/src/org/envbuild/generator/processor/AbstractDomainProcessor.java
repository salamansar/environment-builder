package org.envbuild.generator.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.envbuild.generator.DomainGenerator;
import org.envbuild.generator.RandomGenerator;

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
