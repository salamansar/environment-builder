package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class DoubleRandomizer extends AbstractValueRandomizer<Double> {

    @Override
    public Double generateValue() {
        return getRandom().nextInt(150) * 0.1;
    }

}
