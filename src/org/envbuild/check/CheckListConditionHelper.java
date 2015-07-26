package org.envbuild.check;

import com.google.common.collect.Iterators;

/**
 *
 * @author kovlyashenko
 */
public class CheckListConditionHelper extends DelegateCheckListHelper {
    private final static int ALL_TIMES = -1;
    private final static int NEVER = -2;
    private int times = ALL_TIMES;
    
    protected CheckListConditionHelper(CheckListPropertyHelper innerHelper) {
        super(innerHelper);
    }
    
    public static CheckListConditionHelper get(BooleanCheckOperation<?> op) {
        return new CheckListConditionHelper(CheckListPropertyHelper.createCheck(op));
    }
    
    public CheckListConditionHelper allTimes() {
        times = ALL_TIMES;
        return this;
    }
    
    public CheckListConditionHelper never() {
        times = NEVER;
        return this;
    }
    
    public CheckListConditionHelper atLeast(int number) {
        times = number;
        return this;
    }

    @Override
    public void check(Iterable objectsList) {
        if(times == ALL_TIMES) {
            innerHelper.addCheck(true, Iterators.size(objectsList.iterator()));
        } else if(times == NEVER) {
            innerHelper.addCheck(false, Iterators.size(objectsList.iterator()));
        } else {
            innerHelper.isStrictResultEqual(false);
            innerHelper.addCheck(true, times);
        }
        super.check(objectsList);
    }
    
    

}
