package org.envbuild.exception;

/**
 *
 * @author kovlyashenko
 */
public class PatternBindingException extends RuntimeException {

    public PatternBindingException(String message) {
        super(message);
    }

    public PatternBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatternBindingException(Throwable cause) {
        super(cause);
    }
}
