package org.envbuild.check;

/**
 * @author kovlyashenko
 */
public abstract class AssertCheckOperation<OBJ> extends BooleanCheckOperation<OBJ> {

    @Override
    public Boolean check(OBJ object) {
        checkAsserts(object);
        return true;
    }

    protected abstract void checkAsserts(OBJ object);
    
}
