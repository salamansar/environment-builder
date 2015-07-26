package org.envbuild.generator;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Фасадный сервис для генераторов.
 * @author kovlyashenko
 */
public class GenerationService {
    protected RandomGenerator generator;

    public <T> T generate(Class<T> genClass) throws RuntimeException {
        return generator.generate(genClass);
    }

    public <T> T generate(Class<T> genClass, Object... params) throws RuntimeException {
        return generator.generate(genClass, params);
    }

    public <T> T generate(Class<T> genClass, Boolean isRecursive, Object... params) throws RuntimeException {
        return generator.generate(genClass, isRecursive, params);
    }
    
    public <T> List<T> generateList(Class<T> genClass, int count) throws RuntimeException {
        return GenerationService.this.generateList(genClass, count, false);
    }
    
    public <T> List<T> generateList(Class<T> genClass, int count, Object... params) throws RuntimeException {
        return GenerationService.this.generateList(genClass, count, false, params);
    }
    
    public <T> List<T> generateList(Class<T> genClass, int count, Boolean isRecursive, Object... params) throws RuntimeException {
        List<T> values = new ArrayList<T>(count);
        for(int i = 0; i < count; i++) {
            values.add(generate(genClass, isRecursive, params));            
        }
        return values;
    }
    
    public byte[] generateByteArray(int count) {
        List<Byte> objects = generateList(Byte.class, count);
        return Bytes.toArray(objects);
    }
    
    public short[] generateShortArray(int count) {
        List<Short> objects = generateList(Short.class, count);
        return Shorts.toArray(objects);
    }
    
    public int[] generateIntArray(int count) {
        List<Integer> objects = generateList(Integer.class, count);
        return Ints.toArray(objects);
    }
    
    public long[] generateLongArray(int count) {
        List<Long> objects = generateList(Long.class, count);
        return Longs.toArray(objects);
    }
    
    public double[] generateDoubleArray(int count) {
        List<Double> objects = generateList(Double.class, count);
        return Doubles.toArray(objects);
    }
    
    public float[] generateFloatArray(int count) {
        List<Float> objects = generateList(Float.class, count);
        return Floats.toArray(objects);
    }
    
    public boolean[] generateBooleanArray(int count) {
        List<Boolean> objects = generateList(Boolean.class, count);
        return Booleans.toArray(objects);
    }
    
    
    public RandomGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(RandomGenerator generator) {
        this.generator = generator;
    }
}
