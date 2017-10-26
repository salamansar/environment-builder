package org.envbuild.check;

import org.envbuild.check.BooleanCheckOperation;
import org.envbuild.check.CheckListConditionHelper;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author akovlyashenko
 */
public class CheckListConditionHelperTest {
    
    private List<Integer> checkList = Arrays.<Integer>asList(1,2,3,4,5);
    
    @Test
    public void testCheckAllTimesEntries() {
        CheckListConditionHelper.get(new BooleanCheckOperation<Integer>() {

            @Override
            public Boolean check(Integer object) {
                return object < 6;
            }
        }).allTimes().check(checkList);
    }
    
    @Test
    public void testCheckNeverEntries() {
        CheckListConditionHelper.get(new BooleanCheckOperation<Integer>() {

            @Override
            public Boolean check(Integer object) {
                return object > 6;
            }
        }).never().check(checkList);
    }
    
    @Test
    public void testCheckAtLeastEntries() {
        CheckListConditionHelper.get(new BooleanCheckOperation<Integer>() {

            @Override
            public Boolean check(Integer object) {
                return object <= 3;
            }
        }).atLeast(3).check(checkList);
    }
    
}
