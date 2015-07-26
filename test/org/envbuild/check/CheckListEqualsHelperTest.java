package org.envbuild.check;

import org.envbuild.check.CheckListEqualsHelper;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author akovlyashenko
 */
public class CheckListEqualsHelperTest {
    

    @Test
    public void testCheckReference() {
        Object ref1 = new Object();
        Object ref2 = new Object();
        
        CheckListEqualsHelper.get()
                .addCheck(ref1, 1)
                .addCheck(ref2, 2)
                .check(Arrays.asList(ref2, ref1, ref2));
    }
    
    public static class CheckTestObject {
        private int val;

        public CheckTestObject(int val) {
            this.val = val;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CheckTestObject other = (CheckTestObject) obj;
            if (this.val != other.val) {
                return false;
            }
            return true;
        }
        
    } 
    
    @Test
    public void testCheckEquals() {
        CheckTestObject obj1 = new CheckTestObject(1);
        CheckTestObject obj2 = new CheckTestObject(2);
        
        CheckListEqualsHelper.get()
                .addCheck(new CheckTestObject(1), 1)
                .addCheck(new CheckTestObject(2), 2)
                .check(Arrays.asList(obj2, obj1, obj2));
        
    }
    
    @Test
    public void testCheckPrimitiveType() {
        int a = 1;
        int b = 2;
        
        CheckListEqualsHelper.get()
                .addCheck(a, a)
                .addCheck(b, b)
                .check(Arrays.asList(b, a, b));
    }
    
    @Test
    public void testCheckNonStrict() {
        Object ref1 = new Object();
        Object ref2 = new Object();

        CheckListEqualsHelper.get()
                .addCheck(ref2, 2)
                .isStrictResultEqual(false)
                .check(Arrays.asList(ref2, ref1, ref2));
    }
    
}
