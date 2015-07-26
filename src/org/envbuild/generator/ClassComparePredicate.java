package org.envbuild.generator;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

/**
 * @author kovlyashenko
 */
public class ClassComparePredicate implements Predicate<Object> {
    private Class<?> compareClass;

    public ClassComparePredicate(Class<?> compareClass) {
        this.compareClass = compareClass;
    }

    @Override
    public boolean apply(@Nullable Object input) {
        return input != null && compareClass.isAssignableFrom(input.getClass());
    }
}
