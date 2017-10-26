package org.envbuild.check;

import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Хелпер проверки уникального значения какого-либо поля
 * @author kovlyashenko
 */
public class CheckUniquePropertyHelper implements CheckListHelper {

    CheckOperation operation;

    public CheckUniquePropertyHelper(CheckOperation operation) {
        this.operation = operation;
    }

    public static CheckUniquePropertyHelper create(CheckOperation operation) {
        return new CheckUniquePropertyHelper(operation);
    }

    @Override
    public void check(Iterable objects) {
        Set<Object> checkedSet = new HashSet<Object>();
        for(Object object : objects) {
            Object result = operation.check(object);
            Assert.assertFalse(checkedSet.contains(result));
            checkedSet.add(result);
        }
    }
}
