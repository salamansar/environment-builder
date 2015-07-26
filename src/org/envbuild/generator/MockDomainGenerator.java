package org.envbuild.generator;

import org.envbuild.generator.processor.DomainPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import org.envbuild.common.HasId;

/**
 * @author kovlyashenko
 */
@Component
@DomainGenerator
public class MockDomainGenerator extends RandomGenerator implements BeanPostProcessor {
    private Map<Class<?>, DomainPostProcessor> generatorsRegistry = new HashMap<Class<?>, DomainPostProcessor>();

    @Override
    public <T> T generate(Class<T> genClass, Boolean isRecursive, Object... params) throws RuntimeException {
        T instanse = super.generate(genClass, isRecursive, params);
        DomainPostProcessor processor = generatorsRegistry.get(genClass);
        if(processor != null) {
            processDomain(processor, instanse);            
        } else if(instanse instanceof HasId) {
            ((HasId) instanse).setId(null); //нулевой ид, чтоб можно было без проблем сохранить в базу
        }
        return instanse;
    }

    private <T> void processDomain(DomainPostProcessor processor, T instanse) throws RuntimeException {
        try {
            processor.processDomain(instanse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof DomainPostProcessor) {
            generatorsRegistry.put(((DomainPostProcessor) bean).getDomainClass(), (DomainPostProcessor)bean);
        }
        return bean;
    }
}
