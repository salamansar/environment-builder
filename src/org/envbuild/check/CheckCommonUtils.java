package org.envbuild.check;

import java.util.Collection;
import org.junit.Assert;


/**
 * Утилиты, облегчающие часто встречающиеся проверки
 * @author kovlyashenko
 */
public class CheckCommonUtils {

    public static void checkList(Collection list, int size) {
        Assert.assertNotNull("List is null", list);
        Assert.assertEquals("Checked size and list size mismatched", size, list.size());
    }
}
