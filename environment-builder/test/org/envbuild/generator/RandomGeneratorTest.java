package org.envbuild.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author kovlyashenko
 */
public class RandomGeneratorTest {
    static class A {
        String s1;

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }
    } 
    
    static class B {
        String val;
        A link;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public A getLink() {
            return link;
        }

        public void setLink(A link) {
            this.link = link;
        }
    }
    
    static class C {
        String val;
        C parent;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public C getParent() {
            return parent;
        }

        public void setParent(C parent) {
            this.parent = parent;
        }
    }
    
    static class D extends B {
        String dVal;

        public String getdVal() {
            return dVal;
        }

        public void setdVal(String dVal) {
            this.dVal = dVal;
        }
    }
    
    static class E {
        String val1;
        String val2;
        String val3;

        public String getVal1() {
            return val1;
        }

        public void setVal1(String val1) {
            this.val1 = val1;
        }

        public String getVal2() {
            return val2;
        }

        public String getVal3() {
            return val3;
        }

        public void setVal2Val3(String val2, String val3) {
            this.val2 = val2;
            this.val3 = val3;
        }
        
        
        
    }
    
    RandomGenerator generator = new RandomGenerator();

    @Test
    public void testSimpleNested() {
        generator = new RandomGenerator();
        B test = generator.generate(B.class, true);
    }

    @Test
    public void testCircularNested() {
        generator = new RandomGenerator();
        C test = generator.generate(C.class, true);
    }
    
    @Test
    public void testGenerateBaseTypesForClass() {
        TestBaseTypesClass testObject = generator.generate(TestBaseTypesClass.class);
        Assert.assertNotNull(testObject.isBooleanVal());
        Assert.assertNotNull(testObject.getByteVal());
        Assert.assertNotNull(testObject.getDateVal());
        Assert.assertNotNull(testObject.getDoubleVal());
        Assert.assertNotNull(testObject.getIntVal());
        Assert.assertNotNull(testObject.getLongVal());
        Assert.assertNotNull(testObject.getShortVal());
        Assert.assertNotNull(testObject.getStringVal());
        Assert.assertNotNull(testObject.getFloatVal());
    }
    
    @Test
    public void testGenerateSimpleTypesForClass() {
        TestSimpleTypesClass testObject = generator.generate(TestSimpleTypesClass.class);
        Assert.assertTrue("invalid value: " + testObject.getByteVal(), testObject.getByteVal() >= 0);
        Assert.assertTrue("invalid value: " + testObject.getDoubleVal(), testObject.getDoubleVal() >= 0);
        Assert.assertTrue("invalid value: " + testObject.getIntVal(),testObject.getIntVal() >= 0);
        Assert.assertTrue("invalid value: " + testObject.getLongVal(),testObject.getLongVal() >= 0);
        Assert.assertTrue("invalid value: " + testObject.getShortVal(),testObject.getShortVal() >= 0);
        Assert.assertTrue("invalid value: " + testObject.getFloatVal(),testObject.getFloatVal() >= 0);
    }
    
    @Test
    public void testGenerateBaseTypes() {
        Boolean booleanVal = generator.generate(Boolean.class);
        Byte byteVal = generator.generate(Byte.class);
        Date dateVal = generator.generate(Date.class);
        Double doubleVal = generator.generate(Double.class);
        Integer intVal = generator.generate(Integer.class);
        Long longVal = generator.generate(Long.class);
        Short shortVal = generator.generate(Short.class);
        String stringVal = generator.generate(String.class);
        BigInteger bigIntegerVal = generator.generate(BigInteger.class);
        BigDecimal bigDecimalVal = generator.generate(BigDecimal.class);
        Float floatVal = generator.generate(Float.class);
        
        Assert.assertNotNull(booleanVal);
        Assert.assertNotNull(byteVal);
        Assert.assertNotNull(dateVal);
        Assert.assertNotNull(doubleVal);
        Assert.assertNotNull(intVal);
        Assert.assertNotNull(longVal);
        Assert.assertNotNull(shortVal);
        Assert.assertNotNull(stringVal);
        Assert.assertNotNull(bigIntegerVal);
        Assert.assertNotNull(bigDecimalVal);
        Assert.assertNotNull(floatVal);
    }
    
    @Test
    public void testGeneratePrimitiveTypes() {
        boolean booleanVal = generator.generate(boolean.class);
        byte byteVal = generator.generate(byte.class);
        double doubleVal = generator.generate(double.class);
        int intVal = generator.generate(int.class);
        long longVal = generator.generate(long.class);
        short shortVal = generator.generate(short.class);
        float floatVal = generator.generate(float.class);
    }
    
    @Test
    public void testBigIntegerGeneration() {
        TestBigIntegerClass testClass = generator.generate(TestBigIntegerClass.class);
        Assert.assertNotNull(testClass.getBigIntVal());
        Assert.assertNotNull(testClass.getBigDecimalVal());
		Assert.assertEquals(2, testClass.getBigDecimalVal().scale());
    }
    
    @Test
    public void testUpdateNonRecursive() {
        B instance = new B();
        String str = "trololo";
        instance.setVal(str);
        
        generator.update(instance);
        
        Assert.assertNotNull(instance.getVal());
        Assert.assertFalse(str.equals(instance.getVal()));
        Assert.assertNull(instance.getLink());
    }
    
    @Test
    public void testUpdateRecursively() {
        B instance = new B();
        String str = "trololo";
        A aInstance = new A();
        instance.setVal(str);
        instance.setLink(aInstance);
        
        generator.update(instance, true);
        
        Assert.assertNotNull(instance.getVal());
        Assert.assertFalse(str.equals(instance.getVal()));
        Assert.assertNotNull(instance.getLink());
        Assert.assertNotSame(aInstance, instance.getLink());
        Assert.assertNotNull(instance.getLink().getS1());
    }
    
    @Test
    public void testUpdateWithParameter() {
        B instance = new B();
        String str = "trololo";
        A aInstance = new A();
        instance.setVal(str);
        instance.setLink(aInstance);
        A newInstance = new A();
        
        generator.update(instance, true, newInstance);
        
        Assert.assertNotNull(instance.getVal());
        Assert.assertFalse(str.equals(instance.getVal()));
        Assert.assertNotNull(instance.getLink());
        Assert.assertSame(newInstance, instance.getLink());
    }
    
    @Test
    public void testUpdateAsParentClass() {
        D instance = new D();
        String str = "trololo";        
        instance.setVal(str);
        instance.setdVal(str);
        
        generator.update(instance, true, B.class);
        
        Assert.assertNotNull(instance.getVal());
        Assert.assertFalse(str.equals(instance.getVal()));
        Assert.assertNotNull(instance.getLink());
        Assert.assertNotNull(instance.getLink().getS1());
        Assert.assertEquals(str, instance.getdVal());
    }
    
    @Test
    public void testSetterInstanceFilling() {
        E instance = generator.generate(E.class);
        
        Assert.assertNotNull(instance.getVal1());
        Assert.assertNull(instance.getVal2());
        Assert.assertNull(instance.getVal3());
    }
    
    @Test
    public void testAllMethodsInstanceFilling() {
        generator.setFillingStartegy(InstanceFillingStrategy.ALL_METHODS);
        
        E instance = generator.generate(E.class);

        Assert.assertNotNull(instance.getVal1());
        Assert.assertNotNull(instance.getVal2());
        Assert.assertNotNull(instance.getVal3());
        
        generator.setFillingStartegy(InstanceFillingStrategy.SETTER);
    }
    
}
