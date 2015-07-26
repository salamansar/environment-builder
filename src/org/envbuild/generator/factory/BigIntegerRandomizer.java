package org.envbuild.generator.factory;

import java.math.BigInteger;

/**
 *
 * @author kovlyashenko
 */
public class BigIntegerRandomizer extends AbstractValueRandomizer<BigInteger> {

    @Override
    public BigInteger generateValue() {
        return BigInteger.valueOf(getRandom().nextInt(100500));
    }
    
}
