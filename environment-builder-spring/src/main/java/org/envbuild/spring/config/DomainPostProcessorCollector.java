package org.envbuild.spring.config;

import org.envbuild.generator.MockDomainGenerator;
import org.envbuild.generator.processor.DomainPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author Koshey
 */
public class DomainPostProcessorCollector implements BeanPostProcessor {
    @Autowired
    private MockDomainGenerator domainGenerator;
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DomainPostProcessor) {
            domainGenerator.addDomainProcessor((DomainPostProcessor) bean);
        }
        return bean;
    }
}
