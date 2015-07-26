package org.envbuild.check;

/**
 *
 * @author kovlyashenko
 */
public abstract class DelegateCheckListHelper implements CheckListHelper {
    protected CheckListPropertyHelper innerHelper;

    protected DelegateCheckListHelper(CheckListPropertyHelper innerHelper) {
        this.innerHelper = innerHelper;
    }
    
    public DelegateCheckListHelper isStrictResultEqual(boolean isStrict) {
        innerHelper.isStrictResultEqual(isStrict);
        return this;
    }

    @Override
    public void check(Iterable objects) {
        innerHelper.check(objects);
    }

}
