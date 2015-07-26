package org.envbuild.check;

import com.rits.cloning.Cloner;
import com.rits.cloning.IFastCloner;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author kovlyashenko
 */
public class CheckListPropertyHelper implements CheckListHelper {
    private Map<Object, Integer> checkResultMap = new HashMap<Object, Integer>();
    private Map<Object, Integer> workMap;
    private CheckOperation checkOperation;    
    private boolean isStrictResultEqual = true;

    private Cloner cloner = new Cloner();

    public static CheckListPropertyHelper createCheck(CheckOperation operation) {
        return new CheckListPropertyHelper().newCheckMap(operation);
    }

    public void clear() {
        checkResultMap.clear();
        checkOperation = null;
    }

    public CheckListPropertyHelper newCheckMap(CheckOperation operation) {
        checkResultMap.clear();
        checkOperation = operation;
        return this;
    }

    public CheckListPropertyHelper addCheck(Object result, int count) {
        checkResultMap.put(result, count);
        return this;
    }

    /**
     * флаг говорит о точном совпадении ожидаемого результата и рассчитываемого в ходе проверки Если будет установлен в
     * false, то результаты, которые не описаны в мапе ожидаемых результатов, будут пропускаться
     * @param isStrictResultEqual да. нет
     * @return билдер
     */
    public CheckListPropertyHelper isStrictResultEqual(boolean isStrictResultEqual) {
        this.isStrictResultEqual = isStrictResultEqual;
        return this;
    }
    

    @Override
    public void check(Iterable objectsList) {
        workMap = cloner.deepClone(checkResultMap);
        for (Object object : objectsList) {
            Object result = checkOperation.check(object);
            Integer count = workMap.get(result);
            if(isStrictResultEqual || count != null) {
                Assert.assertNotNull("Such result doesn't present as check result: " + result.toString(), count);
                Assert.assertTrue("Such check result must not appear any more", count > 0);
                workMap.put(result, count - 1);
            }
        }
        for (Entry<Object, Integer> entry : workMap.entrySet()) {
            Assert.assertEquals("Check " + entry.getKey().toString() +" was not found in result set", 
                    0, entry.getValue().intValue());
        }
    }
    
    public CheckListPropertyHelper ignoreClone(Class<?> ignoringClass) {
        cloner.dontClone(ignoringClass);
        return this;
    }
    
}
