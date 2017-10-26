package org.envbuild.check;

import org.junit.Assert;

/**
 *
 * @author kovlyashenko
 */
public class NotNullOperation extends AssertCheckOperation {

    @Override
    protected void checkAsserts(Object object) {
        Assert.assertNotNull(object);
    }
    
}
