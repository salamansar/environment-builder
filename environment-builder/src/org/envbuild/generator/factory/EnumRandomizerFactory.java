package org.envbuild.generator.factory;

/**
 *
 * @author Salamansar
 */
public class EnumRandomizerFactory {
	
	private EnumRandomizer randomzier = new EnumRandomizer();

	public <T extends Enum> ValueRandomizer<T> getRandomizer(final Class<T> generationClass) {
		return new ValueRandomizer<T>() {
			@Override
			public T generateValue() {
				return (T)randomzier.generateValue(generationClass);
			}
		};
	}
	
}
