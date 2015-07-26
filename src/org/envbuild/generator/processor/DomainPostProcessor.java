package org.envbuild.generator.processor;

/**
 * Интерфейс узкоспециализированного генератора для доменов
 * @author kovlyashenko
 */
public interface DomainPostProcessor<T> {
    Class<T> getDomainClass();
    void processDomain(T domain) throws Exception;
}
