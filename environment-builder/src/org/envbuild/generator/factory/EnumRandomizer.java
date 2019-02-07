package org.envbuild.generator.factory;

import java.util.Random;

/**
 *
 * @author Salamansar
 */
public class EnumRandomizer {
	private Random random = new Random();
	
	public <E extends Enum> E generateValue(Class<E> enumClass) {
		E[] values = enumClass.getEnumConstants();
		int index = random.nextInt(values.length);
		return values[index];
	}
}
