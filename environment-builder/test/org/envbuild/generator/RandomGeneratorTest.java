package org.envbuild.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import static org.junit.Assert.*;
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
	
	static enum F {
		FIRST,
		SECOND,
		THIRD
	}
	
	static class G {
		F enumField;
		String strFeild;

		public F getEnumField() {
			return enumField;
		}

		public void setEnumField(F enumField) {
			this.enumField = enumField;
		}

		public String getStrFeild() {
			return strFeild;
		}

		public void setStrFeild(String strFeild) {
			this.strFeild = strFeild;
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
        assertNotNull(testObject.isBooleanVal());
        assertNotNull(testObject.getByteVal());
        assertNotNull(testObject.getDateVal());
        assertNotNull(testObject.getDoubleVal());
        assertNotNull(testObject.getIntVal());
        assertNotNull(testObject.getLongVal());
        assertNotNull(testObject.getShortVal());
        assertNotNull(testObject.getStringVal());
        assertNotNull(testObject.getFloatVal());
    }
    
    @Test
    public void testGenerateSimpleTypesForClass() {
        TestSimpleTypesClass testObject = generator.generate(TestSimpleTypesClass.class);
        assertTrue("invalid value: " + testObject.getByteVal(), testObject.getByteVal() >= 0);
        assertTrue("invalid value: " + testObject.getDoubleVal(), testObject.getDoubleVal() >= 0);
        assertTrue("invalid value: " + testObject.getIntVal(),testObject.getIntVal() >= 0);
        assertTrue("invalid value: " + testObject.getLongVal(),testObject.getLongVal() >= 0);
        assertTrue("invalid value: " + testObject.getShortVal(),testObject.getShortVal() >= 0);
        assertTrue("invalid value: " + testObject.getFloatVal(),testObject.getFloatVal() >= 0);
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
        
        assertNotNull(booleanVal);
        assertNotNull(byteVal);
        assertNotNull(dateVal);
        assertNotNull(doubleVal);
        assertNotNull(intVal);
        assertNotNull(longVal);
        assertNotNull(shortVal);
        assertNotNull(stringVal);
        assertNotNull(bigIntegerVal);
        assertNotNull(bigDecimalVal);
        assertNotNull(floatVal);
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
        assertNotNull(testClass.getBigIntVal());
        assertNotNull(testClass.getBigDecimalVal());
		assertEquals(2, testClass.getBigDecimalVal().scale());
    }
    
    @Test
    public void testUpdateNonRecursive() {
        B instance = new B();
        String str = "trololo";
        instance.setVal(str);
        
        generator.update(instance);
        
        assertNotNull(instance.getVal());
        assertFalse(str.equals(instance.getVal()));
        assertNull(instance.getLink());
    }
    
    @Test
    public void testUpdateRecursively() {
        B instance = new B();
        String str = "trololo";
        A aInstance = new A();
        instance.setVal(str);
        instance.setLink(aInstance);
        
        generator.update(instance, true);
        
        assertNotNull(instance.getVal());
        assertFalse(str.equals(instance.getVal()));
        assertNotNull(instance.getLink());
        assertNotSame(aInstance, instance.getLink());
        assertNotNull(instance.getLink().getS1());
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
        
        assertNotNull(instance.getVal());
        assertFalse(str.equals(instance.getVal()));
        assertNotNull(instance.getLink());
        assertSame(newInstance, instance.getLink());
    }
    
    @Test
    public void testUpdateAsParentClass() {
        D instance = new D();
        String str = "trololo";        
        instance.setVal(str);
        instance.setdVal(str);
        
        generator.update(instance, true, B.class);
        
        assertNotNull(instance.getVal());
        assertFalse(str.equals(instance.getVal()));
        assertNotNull(instance.getLink());
        assertNotNull(instance.getLink().getS1());
        assertEquals(str, instance.getdVal());
    }
    
    @Test
    public void testSetterInstanceFilling() {
        E instance = generator.generate(E.class);
        
        assertNotNull(instance.getVal1());
        assertNull(instance.getVal2());
        assertNull(instance.getVal3());
    }
    
    @Test
    public void testAllMethodsInstanceFilling() {
        generator.setFillingStartegy(InstanceFillingStrategy.ALL_METHODS);
        
        E instance = generator.generate(E.class);

        assertNotNull(instance.getVal1());
        assertNotNull(instance.getVal2());
        assertNotNull(instance.getVal3());
        
        generator.setFillingStartegy(InstanceFillingStrategy.SETTER);
    }
    
	@Test
	public void testGenerateWithObjectSubstitution() {
		A obj = new A();
		
		B instance = generator.generate(B.class, obj);
		
		assertNotNull(instance.link);
		assertSame(obj, instance.link);
	}
	
	@Test
	public void testGenerateWithSimpleTypeSubstitution() {
		String str = "someStr";
		
		B instance = generator.generate(B.class, true, str);

		assertNotNull(instance.link);
		assertEquals(str, instance.val);
	}
	
	@Test
	public void testGenerateEnum() {
		F instance = generator.generate(F.class);
		for(int count = 0; instance != F.FIRST; count++, instance = generator.generate(F.class)) {
			if(count == 50) {
				fail("Value " + F.FIRST + " wasn't be generated");
			}
		}
		for(int count = 0; instance != F.SECOND; count++, instance = generator.generate(F.class)) {
			if(count == 50) {
				fail("Value " + F.SECOND + " wasn't be generated");
			}
		}
		for(int count = 0; instance != F.THIRD; count++, instance = generator.generate(F.class)) {
			if(count == 50) {
				fail("Value " + F.THIRD + " wasn't be generated");
			}
		}
		
		assertNotNull(instance);
	}
	
	@Test
	public void testGenerateClassWithEum() {
		G instance = generator.generate(G.class);
		
		assertNotNull(instance);
		assertNotNull(instance.strFeild);
		assertNotNull(instance.enumField);
	}
	
	@Test
	public void testSubstitutionForEum() {
		G instance = generator.generate(G.class, F.SECOND);
		
		assertNotNull(instance);
		assertNotNull(instance.strFeild);
		assertNotNull(instance.enumField);
		assertEquals(F.SECOND, instance.enumField);
	}
	
	
}
