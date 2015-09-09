package org.envbuild.check;

import com.rits.cloning.Cloner;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для проверки на наличие типов объектов в листе
 * Например, есть лист, где хранятся объекты разных типов,
 * причем количество этих объектов по типам известно,
 * а вот как раз порядок объектов не известен.
 * В этом случае сконфигурировав этот хелпер можно это проверить.
 * Пример использования:
 * <pre>
 *     //есть лист объектов
 *     List&lt;ParentClass&gt; objects = getListObjects();
 *     //создаем и настраиваем хелпер
 *     CheckListHelper.create()
 *          .addTypeCheck(ChildClass1.class, 3) //3 штуки дочернего класса
 *          .addTypeCheck(ChildClass2.class, 5) //5 штук другого класса
 *          .check(objects);
 * </pre>
 * После этого лист будет проверен на соответствие этим параметрам.
 *
 * Может случиться,что класс объекта не является целью проверки, а проверить нужно вложенный объект.
 * В этом случае можно задать для хелпера объект, который будет выдавать нужный тип ({@link TypeResolver}
 * Пример:
 * <pre>
 *     class SomeClass {
 *         ParentNestedClass dto;
 *     }
 *
 *     ...
 *
 *     //получаем лист объектов
 *     List&lt;SomeClass&gt; objects = getListObjects();
 *     CheckListHelper.create()
 *          .addTypeCheck(ChildNestedClass1.class, 3)
 *          .addTypeCheck(ChildNestedClass2.class, 5)
 *          .useTypeResolver(new TypeResolver&lt;SomeClass&gt; () {
 *              public Class&lt;?&gt; getObjectClass(SomeClass object) {
 *                  return object.dto.getClass();
 *              }
 *          })
 *
 * </pre>
 * @author kovlyashenko
 */
public class CheckListTypeHelper implements CheckListHelper {
    private Map<Class<?>, Integer> checkMap = new HashMap<Class<?>, Integer>();
    private Map<Class<?>, Integer> workMap;
    private TypeResolver typeResolver;

    private Cloner cloner = new Cloner();

    public interface TypeResolver<T> {
        Class<?> getObjectClass(T object);
    }

    public static CheckListTypeHelper create() {
        return new CheckListTypeHelper();
    }

    public void clear() {
        checkMap.clear();
        typeResolver = null;
    }

    public CheckListTypeHelper newMap() {
        checkMap.clear();
        return this;
    }

    public CheckListTypeHelper addTypeCheck(Class<?> objectClass, int count) {
        checkMap.put(objectClass, count);
        return this;
    }

    public CheckListTypeHelper useTypeResolver(TypeResolver resolver) {
        this.typeResolver = resolver;
        return this;
    }

    @Override
    public void check(Iterable objectsList) {
        workMap = cloner.deepClone(checkMap);
        initResolver();
        for (Object object : objectsList) {
            Class<?> objectClass = typeResolver.getObjectClass(object);
            Integer count = workMap.get(objectClass);
            Assert.assertNotNull(count);
            Assert.assertTrue(count > 0);
            workMap.put(objectClass, count - 1);
        }
        for (Integer count : workMap.values()) {
            Assert.assertEquals(0, count.intValue());
        }
    }

    private void initResolver() {
        if(typeResolver == null) {
            typeResolver = new TypeResolver() {
                @Override
                public Class<?> getObjectClass(Object object) {
                    return object.getClass();
                }
            };
        }
    }
}
