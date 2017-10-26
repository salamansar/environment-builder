package org.envbuild.generator.factory;

import java.lang.reflect.ParameterizedType;
import java.util.Random;

/**
 *
 * @author kovlyashenko
 */
public abstract class AbstractValueRandomizer<T> implements ValueRandomizer<T> {
    
    protected Random random = new Random();

    public Random getRandom() {
        return random;
    }
    
}
