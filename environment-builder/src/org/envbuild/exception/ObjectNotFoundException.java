package org.envbuild.exception;

/**
 *
 * @author kovlyashenko
 */
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String string) {
        super(string);
    }

    public ObjectNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ObjectNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

}
