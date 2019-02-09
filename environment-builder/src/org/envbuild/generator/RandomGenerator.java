package org.envbuild.generator;

import com.google.common.collect.Iterables;
import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import org.envbuild.generator.factory.RandomizerFactory;

/**
 * The component is for filling simple fields of objects with random values. <br>
 * There are some constraints exists:
 * <ul>
 * <li>Generic fields aren't filling</li>
 * <li>A class must be accessible for the generator, i.e. must have "public" modifier </li>
 * <li>A class must have a public constructor without arguments </li>
 * </ul>
 * 
 * @author Salamansar
 */
public class RandomGenerator {
    private RandomizerFactory randomizerFactory = new RandomizerFactory();
    private Set<Method> visitedMethods = new HashSet<Method>();
    private InstanceFillingStrategy fillingStartegy = InstanceFillingStrategy.SETTER;

    public RandomGenerator() {
        randomizerFactory.initialize();
    }

    public <T> T generate(Class<T> genClass) throws RuntimeException {
        return generate(genClass, false);
    }

    public <T> T generate(Class<T> genClass, Object ...params) throws RuntimeException {
        return generate(genClass, false, params);
    }

    public <T> T generate(Class<T> genClass, Boolean isRecursive, Object ...params) throws RuntimeException {
        visitedMethods.clear();
        try {
            T generatedObject = internalGenerate(genClass, isRecursive, params);
            return generatedObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T internalGenerate(Class<T> genClass, Boolean isRecursive, Object ...param) throws Exception {
        if(randomizerFactory.hasRandomizer(genClass)) {
            return (T)randomizerFactory.getRandomizer(genClass).generateValue();
        } 
        T instance = genClass.newInstance();
        internalUpdate(instance, genClass, isRecursive, param);
        return instance;
    }

    private <T> void internalUpdate(T instance, Class<T> genClass, Boolean isRecursive, Object... param) throws SecurityException, Exception {
        Method[] methods = genClass.getMethods();
        
        for(Method method : methods) {
            if(method.getReturnType() == void.class && method.getDeclaringClass() != Object.class) {
                Class<?>[] classParams = method.getParameterTypes();
                if(classParams.length > 0 
                        && (classParams.length == 1 
                            || fillingStartegy == InstanceFillingStrategy.ALL_METHODS)) {
                    invokeMethod(method, instance, param, isRecursive);
                }
            }
        }
    }
    
    private <T> void invokeMethod(Method method, T instance, Object[] param, Boolean isRecursive) throws Exception, InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        Class<?>[] classParams = method.getParameterTypes();
        Object[] params = new Object[classParams.length];
        boolean isInvoke = true;
        for(int i = 0; i < params.length; i++) {
			if (param != null 
					&& param.length > 0 
					&& !(classParams[i] == Object.class) ) {
				
				params[i] = Iterables.find(Arrays.asList(param),
						new ClassComparePredicate(classParams[i]), null);
			}
			
			if(params[i] == null) {
				if(randomizerFactory.hasRandomizer(classParams[i])) {
					params[i] = randomizerFactory.getRandomizer(classParams[i]).generateValue();
				} else if(isRecursive && !(classParams[i] == Object.class)) {
					if(!visitedMethods.contains(method)
							&& !Modifier.isAbstract(classParams[i].getModifiers())
							&& !classParams[i].isArray()) {

						visitedMethods.add(method);
						try {
							params[i] = internalGenerate(classParams[i], true, params);
						} catch (InstantiationException ex) {
							params[i] = null;
						}
					}

					isInvoke = params[i] != null;
					if(!isInvoke) {
						break;
					}
				} else {
					isInvoke = false;
					break;
				}
			}
        }
        if(isInvoke) {
            method.invoke(instance, params);
        }
    }
   
    public void update(Object object, Object... params) {
        update(object, false, params);
    }    
    
    public void update(Object object, Boolean isRecursive, Object... params) {
        update(object, isRecursive, object.getClass(), params);
    }    
    
    public void update(Object object, Boolean isRecursive, Class objectClass, Object... params) throws RuntimeException {
        visitedMethods.clear();
        try{
            internalUpdate(object, objectClass, isRecursive, params);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public InstanceFillingStrategy getFillingStartegy() {
        return fillingStartegy;
    }

    public void setFillingStartegy(InstanceFillingStrategy fillingStartegy) {
        this.fillingStartegy = fillingStartegy;
    }
    
}
