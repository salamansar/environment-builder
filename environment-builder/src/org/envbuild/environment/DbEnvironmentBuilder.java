package org.envbuild.environment;

import com.google.common.collect.Maps;
import org.envbuild.generator.RandomGenerator;

import java.util.HashMap;
import java.util.Map;
import org.envbuild.generator.processor.DomainPersister;

/**
 * The builder is for creating DbEnvironment and persist them into a database. <br>
 * Examples of usage:
 * TODO: fill examples
 * @author Salamansar
 */
public class DbEnvironmentBuilder {
    protected RandomGenerator randomGenerator;
    protected DomainPersister domainPersister;
    protected DbEnvironment environment;
	
	protected Map<Class<?>, Object> parentsMap = new HashMap<Class<?>, Object>(5);
	private Object lastObject;

    public DbEnvironmentBuilder() {
    }

    public DbEnvironmentBuilder(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public DbEnvironmentBuilder(RandomGenerator randomGenerator, DomainPersister domainPersister) {
        this.randomGenerator = randomGenerator;
        this.domainPersister = domainPersister;
    }

    public DbEnvironmentBuilder newBuild() {
        return newBuild(new DbEnvironment());
    }

    public DbEnvironmentBuilder newBuild(DbEnvironment environment) {
        this.environment = environment;
        parentsMap.clear();
        lastObject = null;
        return this;
    }

    public DbEnvironmentBuilder createObject(Class<?> className, Object ...params) throws RuntimeException {
        return createObject(className, true, params);
    }

    public DbEnvironmentBuilder createObject(Class<?> className, Boolean isPersist, Object ...params) throws RuntimeException {
        Object instance = generateObject(className, params);
        addIntoEnvironment(className, instance, isPersist);
        return this;
    }
	
	private Object generateObject(Class<?> className, Object... params) {
		Map<Class<?>, Object> paramsMap = getCombinedParams(params);
		return randomGenerator.generate(className, paramsMap.values().toArray());
	}
	
	private void addIntoEnvironment(Class<?> className, Object instance, Boolean isPersist) {
		environment.addObject(className, instance);
		lastObject = instance;
		if (isPersist && domainPersister != null) {
			domainPersister.persistDomain(lastObject);
		}
	}
	
	public DbEnvironmentBuilder createObject(Class<?> className, ObjectCustomizer customizer) throws RuntimeException {
		return createObject(className, true, customizer);
	}
	
    public DbEnvironmentBuilder createObject(Class<?> className, Boolean isPersist, ObjectCustomizer customizer) throws RuntimeException {
		Object instance = generateObject(className);
		customizer.customize(instance);
		addIntoEnvironment(className, instance, isPersist);
        return this;
    }
	
	private Map<Class<?>, Object> getCombinedParams(Object[] params) {
		if(params == null || params.length == 0) {
			return parentsMap;
		} else {
			HashMap<Class<?>, Object> resultParams = Maps.newHashMap(parentsMap);
			for(Object param : params) {
				resultParams.put(param.getClass(), param);
			}
			return resultParams;
		}
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
		lastObject = object;
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
