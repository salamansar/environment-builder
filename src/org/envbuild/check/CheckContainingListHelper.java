package org.envbuild.check;

import java.util.List;
import org.junit.Assert;

/**
 *
 * @author kovlyashenko
 */
public class CheckContainingListHelper implements CheckListHelper {

    private CheckOperation operation;
    private boolean isContain;
    private Object searchResult;

    public CheckContainingListHelper(CheckOperation operation, boolean isContain) {
        this.operation = operation;
        this.isContain = isContain;
    }
    
    public static CheckContainingListHelper isContain(CheckOperation operation) {
        return new CheckContainingListHelper(operation, true);
    }
    
    public static CheckContainingListHelper isNotContain(CheckOperation operation) {
        return new CheckContainingListHelper(operation, false);
    }
    
    public CheckContainingListHelper value(Object result) {
        this.searchResult = result;
        return this;
    }
    
    @Override
    public void check(Iterable objects) {
        for (Object object : objects) {
            Object result = operation.check(object);
            if(result.equals(searchResult)) {
                Assert.assertTrue("Such result " + searchResult.toString() + " present in checked list", isContain);
                break;
            }
        }
        Assert.assertFalse("Such result " + searchResult.toString() + " is not present in result list", isContain);
    }
    
    

}
