package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class StringRandomizer extends AbstractValueRandomizer<String> {

    @Override
    public String generateValue() {
        return "random" + getRandom().nextInt(150);
    }
    
}
