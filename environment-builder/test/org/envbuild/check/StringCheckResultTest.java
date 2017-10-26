package org.envbuild.check;

import org.junit.Assert;
import org.junit.Test;
import org.envbuild.check.StringCheckResult;

/**
 * @author kovlyashenko
 */
public class StringCheckResultTest {

    @Test
    public void testGetOtherOrder() throws Exception {
        long val1 = 1;
        long val2 = 4;

        StringCheckResult res1 = StringCheckResult.create(val1, 0, false, true);
        StringCheckResult res2 = StringCheckResult.create(val2, 0, false, true);

        Assert.assertTrue(res1.hashCode() != res2.hashCode());
    }
    
    @Test
    public void testAddResultValue() {
        long val1 = 1;
        long val2 = 4;
        
        StringCheckResult result = new StringCheckResult();
        result.addResult(val1).addResult(val2);
        
        Assert.assertTrue(result.toString().equals("1.4."));
    }
    
}
