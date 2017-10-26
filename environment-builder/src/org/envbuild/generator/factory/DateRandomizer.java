package org.envbuild.generator.factory;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kovlyashenko
 */
public class DateRandomizer extends AbstractValueRandomizer<Date> {

    @Override
    public Date generateValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, getRandom().nextInt(365) * -1);
        calendar.set(Calendar.HOUR_OF_DAY, getRandom().nextInt(23));
        calendar.set(Calendar.MINUTE, getRandom().nextInt(60));
        return calendar.getTime();
    }

}
