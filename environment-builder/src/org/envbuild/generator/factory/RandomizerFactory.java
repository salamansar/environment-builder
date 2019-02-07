package org.envbuild.generator.factory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kovlyashenko
 */
public class RandomizerFactory {
    
    private Map<Class, ValueRandomizer> randomizersMap = new HashMap<Class, ValueRandomizer>();
	private EnumRandomizerFactory enumRandomizerFactory = new EnumRandomizerFactory();
    
    public <T> ValueRandomizer<T> getRandomizer(Class<T> generateClass) {
		if (generateClass.isEnum()) {
			return (ValueRandomizer<T>) enumRandomizerFactory.getRandomizer(generateClass.asSubclass(Enum.class));
		} else {
			return randomizersMap.get(generateClass);
		}
    }
    
    public void initialize() {
        randomizersMap.put(Integer.class, new IntegerRandomizer());
        randomizersMap.put(Long.class, new LongRandomizer());
        randomizersMap.put(Double.class, new DoubleRandomizer());
        randomizersMap.put(Byte.class, new ByteRandomizer());
        randomizersMap.put(Short.class, new ShortRandomizer());
        randomizersMap.put(String.class, new StringRandomizer());
        randomizersMap.put(Boolean.class, new BooleanRandomizer());
        randomizersMap.put(Date.class, new DateRandomizer());
        randomizersMap.put(BigInteger.class, new BigIntegerRandomizer());
        randomizersMap.put(BigDecimal.class, new BigDecimalRandomizer());
        randomizersMap.put(Float.class, new FloatRandomizer());
        //fill primitive types
        randomizersMap.put(Integer.TYPE, randomizersMap.get(Integer.class));
        randomizersMap.put(Long.TYPE, randomizersMap.get(Long.class));
        randomizersMap.put(Double.TYPE, randomizersMap.get(Double.class));
        randomizersMap.put(Byte.TYPE, randomizersMap.get(Byte.class));
        randomizersMap.put(Short.TYPE, randomizersMap.get(Short.class));
        randomizersMap.put(Boolean.TYPE, randomizersMap.get(Boolean.class));
        randomizersMap.put(Float.TYPE, randomizersMap.get(Float.class));
    }
    
    public boolean hasRandomizer(Class generateClass) {
        return generateClass.isEnum() || randomizersMap.containsKey(generateClass);
    }

}
