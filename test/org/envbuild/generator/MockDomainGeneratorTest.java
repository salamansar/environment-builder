package org.envbuild.generator;

import org.envbuild.generator.processor.DomainPostProcessor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Salamansar
 */
public class MockDomainGeneratorTest {
    static class A {
        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }
        
    }
    
    static class B extends A {
        private Long field2;

        public Long getField2() {
            return field2;
        }

        public void setField2(Long field2) {
            this.field2 = field2;
        }
        
    }
    
    static class C extends A {
        private Long field3;

        public Long getField3() {
            return field3;
        }

        public void setField3(Long field3) {
            this.field3 = field3;
        }
        
    }
    
    static class D {
        private Long field4;

        public Long getField4() {
            return field4;
        }

        public void setField4(Long field4) {
            this.field4 = field4;
        }
        
    }
    
    private MockDomainGenerator generator;
    private String normalValue = "Some normal value";
    private Long longValue = -1L;
    
    DomainPostProcessor<A> aProcessor = new DomainPostProcessor<A>() {
        @Override
        public Class<A> getDomainClass() {
            return A.class;
        }

        @Override
        public void processDomain(A domain) throws Exception {
            domain.setField1(normalValue);
        }
    };
    
    DomainPostProcessor<B> bProcessor = new DomainPostProcessor<B>() {
        @Override
        public Class<B> getDomainClass() {
            return B.class;
        }

        @Override
        public void processDomain(B domain) throws Exception {
            domain.setField2(longValue);
        }
    };
    
    @Before
    public void setUp() {
        generator = new MockDomainGenerator();
        generator.postProcessAfterInitialization(aProcessor, "aProcessor");
        generator.postProcessAfterInitialization(bProcessor, "bProcessor");
    }
    

    @Test
    public void generateA() {
        
        A aGenerated = generator.generate(A.class);
        
        assertNotNull(aGenerated);
        assertEquals(normalValue, aGenerated.getField1());
    }
    
    @Test
    public void generateB() {
        
        B bGenerated = generator.generate(B.class);
        
        assertNotNull(bGenerated);
        assertNotNull(bGenerated.getField1());
        assertNotEquals(normalValue, bGenerated.getField1());
        assertEquals(longValue, bGenerated.getField2());
    }
    
    @Test
    public void generateC() {
        
        C cGenerated = generator.generate(C.class);
        
        assertNotNull(cGenerated);
        assertEquals(normalValue, cGenerated.getField1());
        assertNotNull(cGenerated.getField3());
        assertNotEquals(longValue, cGenerated.getField3());
    }
    
    @Test
    public void generateD() {
        
        D dGenerated = generator.generate(D.class);
        
        assertNotNull(dGenerated);
        assertNotEquals(normalValue, dGenerated.getField4());
    }
    
}
