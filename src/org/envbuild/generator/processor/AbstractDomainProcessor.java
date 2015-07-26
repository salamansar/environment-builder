package org.envbuild.generator.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.envbuild.generator.DomainGenerator;
import org.envbuild.hibernate.SessionFactory;
import org.envbuild.generator.RandomGenerator;

/**
 * @author kovlyashenko
 */
public abstract class AbstractDomainProcessor<T> implements DomainPostProcessor<T> {
    private SessionFactory sessionFactory;    
    private RandomGenerator randomGenerator;

    public SessionFactory sessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
