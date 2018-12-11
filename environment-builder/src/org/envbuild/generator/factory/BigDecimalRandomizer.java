package org.envbuild.generator.factory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author kovlyashenko
 */
public class BigDecimalRandomizer extends AbstractValueRandomizer<BigDecimal> {

    @Override
    public BigDecimal generateValue() {
        return new BigDecimal(getRandom().nextInt(100500) * 0.1)
				.setScale(2, RoundingMode.FLOOR);
    }
    
}
