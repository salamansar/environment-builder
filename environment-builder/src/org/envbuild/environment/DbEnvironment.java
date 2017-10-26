package org.envbuild.environment;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.envbuild.exception.ObjectNotFoundException;

/**
 * Струкутура, в которой хранятся объекты, необходимые для обработки.
 * @author kovlyashenko
 */
public class DbEnvironment {
    protected Multimap<Class<?>, Object> elementsHolder = ArrayListMultimap.create();
    private Map<Object, Object> savedObjects = new HashMap<Object, Object>();

    public void clear() {
        elementsHolder.clear();
        savedObjects.clear();
    }
    
    public void addObject(Class<?> className, Object object) {
        elementsHolder.put(className, object);
    }
    
    public void addAlias(Object object, Object alias) {
        savedObjects.put(alias, object);
    }

    public <T>List<T> findObjects(Class<T> className) {
        return (List<T>)elementsHolder.get(className);
    }

    /**
     * Получить по алиасу объект
     * @param <T> тип
     * @param id алиас
     * @return объект
     * @throws ObjectNotFoundException если объекта по алиасу не найдено
     */
    public <T>T getByAlias(Object id) throws ObjectNotFoundException {
        T component = tryGetByAlias(id);
        if(component == null) {
            throw new ObjectNotFoundException("Object with alias = " + id.toString() + " not found.");
        } else {
            return component;
        }
    }
    
    /**
     * Получить по алиасу объект.
     * @param <T> тип
     * @param id алиас
     * @return объект или null, если нет такого объекта
     */
    public <T>T tryGetByAlias(Object id) {
        return (T)savedObjects.get(id);
    }
}
