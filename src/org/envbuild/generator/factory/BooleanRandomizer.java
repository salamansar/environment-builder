package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class BooleanRandomizer extends AbstractValueRandomizer<Boolean> {

    @Override
    public Boolean generateValue() {
        return getRandom().nextBoolean();
    }
    
}
