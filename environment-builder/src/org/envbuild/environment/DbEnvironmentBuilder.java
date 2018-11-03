package org.envbuild.environment;

import org.envbuild.generator.RandomGenerator;

import java.util.HashMap;
import java.util.Map;
import org.envbuild.generator.processor.DomainPersister;

/**
 * @author kovlyashenko
 */
public class DbEnvironmentBuilder {
    protected RandomGenerator randomGenerator;
    protected DomainPersister domainPersister;
    protected DbEnvironment environment;

    public DbEnvironmentBuilder() {
    }

    public DbEnvironmentBuilder(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public DbEnvironmentBuilder(RandomGenerator randomGenerator, DomainPersister domainPersister) {
        this.randomGenerator = randomGenerator;
        this.domainPersister = domainPersister;
    }

    protected Map<Class<?>, Object> parentsMap = new HashMap<Class<?>, Object>(5);
    private Object lastObject;


    public DbEnvironmentBuilder newBuild() {
        return newBuild(new DbEnvironment());
    }

    public DbEnvironmentBuilder newBuild(DbEnvironment environment) {
        this.environment = environment;
        parentsMap.clear();
        lastObject = null;
        return this;
    }

    public DbEnvironmentBuilder createObject(Class<?> className) throws RuntimeException {
        return createObject(className, true);
    }

    public DbEnvironmentBuilder createObject(Class<?> className, boolean isPersist) throws RuntimeException {
        Object instance = randomGenerator.generate(className, parentsMap.values().toArray());
        environment.addObject(className, instance);
        lastObject = instance;
        if (isPersist) {
            domainPersister.persistDomain(lastObject);
        }
        return this;
    }

    public DbEnvironmentBuilder asParent(Class<?> className) {
        return setParent(lastObject, className);
    }

    public DbEnvironmentBuilder asParent() {
        return setParent(lastObject);
    }

    public DbEnvironmentBuilder setParent(Object object) {
        return setParent(object, object.getClass());
    }

    public DbEnvironmentBuilder setParent(Object object, Class<?> className) {
        parentsMap.remove(className);
        parentsMap.put(className, object);
        return this;
    }

    public DbEnvironmentBuilder setSavedParent(Object alias) {
        return setSavedParent(alias, null);
    }

    public DbEnvironmentBuilder setSavedParent(Object alias, Class<?> className) {
        Object object = environment.getByAlias(alias);        
        if (className == null) {
            setParent(object);
        } else {
            setParent(object, className);
        }
        return this;
    }

    public DbEnvironmentBuilder clearParent(Class<?> className) {
        parentsMap.remove(className);
        return this;
    }

    public DbEnvironmentBuilder clearParents() {
        parentsMap.clear();
        return this;
    }

    public DbEnvironmentBuilder alias(Object id) {
        environment.addAlias(lastObject, id);
        return this;
    }

    public DbEnvironment getEnvironment() {
        return environment;
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public DomainPersister getDomainPersister() {
        return domainPersister;
    }

    public void setDomainPersister(DomainPersister domainPersister) {
        this.domainPersister = domainPersister;
    }
    
}
