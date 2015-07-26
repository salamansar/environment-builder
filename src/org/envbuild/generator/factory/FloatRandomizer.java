package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class FloatRandomizer extends AbstractValueRandomizer<Float> {

    @Override
    public Float generateValue() {
        return getRandom().nextInt(150) * 0.1f;
    }
    
}
