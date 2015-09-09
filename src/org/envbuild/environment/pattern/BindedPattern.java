package org.envbuild.environment.pattern;

import org.envbuild.exception.PatternInitializationException;

/**
 * Паттерн, который имеет привязку к каким-либо объектам или окружению.
 * @author kovlyashenko
 */
public interface BindedPattern {

    /**
     * Привязан ли паттерн к целевым объектам
     * @return true - привязан, false - нет
     */
    boolean isBinded();
    
    /**
     * Произвести инициализацию паттерна - настроить его на работу с привязкой
     * @throws org.envbuild.exception.PatternInitializationException ошибка инициализации
     */
    void initializePattern() throws PatternInitializationException ;
}
