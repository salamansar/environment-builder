package org.envbuild.exception;

/**
 * Эксепшен того, что паттерн не был верно проинициализирован перед своей работой
 * @author kovlyashenko
 */
public class PatternInitializationException extends RuntimeException {

    public PatternInitializationException(Class patternClass) {
        this(patternClass.getName());
    }

    public PatternInitializationException(String patternName) {
        super("Pattern " + patternName + "  was not initialized");
    }

    public PatternInitializationException(String patternName, Throwable cause) {
        super("Pattern " + patternName + "  was not initialized", cause);
    }
}
