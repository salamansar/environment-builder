package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class LongRandomizer extends AbstractValueRandomizer<Long> {

    @Override
    public Long generateValue() {
        return new Integer(getRandom().nextInt(150)).longValue();
    }

}
