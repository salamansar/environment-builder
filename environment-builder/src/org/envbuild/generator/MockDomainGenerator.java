package org.envbuild.generator;

import org.envbuild.generator.processor.DomainPostProcessor;

import java.util.HashMap;
import java.util.Map;
import org.envbuild.common.HasId;

/**
 * @author kovlyashenko
 */
public class MockDomainGenerator extends RandomGenerator {
    private Map<Class<?>, DomainPostProcessor> generatorsRegistry = new HashMap<Class<?>, DomainPostProcessor>();

    @Override
    public <T> T generate(Class<T> genClass, Boolean isRecursive, Object... params) throws RuntimeException {
        T instanse = super.generate(genClass, isRecursive, params);
        DomainPostProcessor processor = findProcessor(genClass);
        if(processor != null) {
            processDomain(processor, instanse);            
        } else if(instanse instanceof HasId) {
            //todo: replace with trying find by name
            ((HasId) instanse).setId(null); //нулевой ид, чтоб можно было без проблем сохранить в базу
        }
        return instanse;
    }

    private <T> DomainPostProcessor findProcessor(Class<T> genClass) {
        if(genClass == Object.class) {
            return null; //for Object can't be any processor
        }
        DomainPostProcessor processor = generatorsRegistry.get(genClass);
        if(processor == null) { //if not found, try found processor for parent class
            processor = findProcessor(genClass.getSuperclass());
        }
        return processor;
    }

    private <T> void processDomain(DomainPostProcessor processor, T instanse) throws RuntimeException {
        try {
            processor.processDomain(instanse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void addDomainProcessor(DomainPostProcessor processor) {
        generatorsRegistry.put(processor.getDomainClass(), processor);
    }
    
}
