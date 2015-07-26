package org.envbuild.environment.pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kovlyashenko
 */
@Component
public class DbEnvironmentPatternRegistry implements BeanPostProcessor {
    private Map<String, DbEnvironmentPattern> patternMap = new HashMap<String, DbEnvironmentPattern>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof DbEnvironmentPattern) {
            patternMap.put(((DbEnvironmentPattern) bean).getName(), (DbEnvironmentPattern) bean);
        }
        return bean;
    }

    public DbEnvironmentPattern findPattern(String patternName) {
        return patternMap.get(patternName);
    }
}
