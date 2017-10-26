package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class IntegerRandomizer extends AbstractValueRandomizer<Integer> {

    @Override
    public Integer generateValue() {
        return getRandom().nextInt(150);
    }
}
