package org.envbuild.check;

/**
 *
 * @author kovlyashenko
 */
public class CheckListEqualsHelper extends DelegateCheckListHelper {
    
    static class CheckWrapper {
        Object target;

        public CheckWrapper(Object target) {
            this.target = target;
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null || !(obj instanceof CheckWrapper)) {
                return false;
            } 
            return target.equals(((CheckWrapper)obj).target);
        }
        
    }

    protected CheckListEqualsHelper(CheckListPropertyHelper innerHelper) {        
        super(innerHelper.ignoreClone(CheckWrapper.class));
    }

    public static CheckListEqualsHelper get() {
        return new CheckListEqualsHelper(CheckListPropertyHelper.createCheck(new CheckOperation() {
            @Override
            public Object check(Object object) {
                return new CheckWrapper(object);
            }
        }));
    }
    
    public CheckListEqualsHelper addCheck(Object expected, int count) {
        innerHelper.addCheck(new CheckWrapper(expected), count);
        return this;
    }

    @Override
    public CheckListEqualsHelper isStrictResultEqual(boolean isStrict) {
        super.isStrictResultEqual(isStrict);
        return this;
    }

}
