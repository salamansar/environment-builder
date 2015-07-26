package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class ShortRandomizer extends AbstractValueRandomizer<Short> {

    @Override
    public Short generateValue() {
        return new Integer(getRandom().nextInt(150)).shortValue();
    }
}
