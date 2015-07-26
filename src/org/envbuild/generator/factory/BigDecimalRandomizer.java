package org.envbuild.generator.factory;

import java.math.BigDecimal;

/**
 *
 * @author kovlyashenko
 */
public class BigDecimalRandomizer extends AbstractValueRandomizer<BigDecimal> {

    @Override
    public BigDecimal generateValue() {
        return new BigDecimal(getRandom().nextInt(100500) * 0.1);
    }
    
}
