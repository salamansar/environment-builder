package org.envbuild.check;

/**
 * @author kovlyashenko
 */
public class StringCheckResult {
    private String value = "";
    private String toStringValue = "";

    public static StringCheckResult create(Object... values) {
        StringCheckResult result = new StringCheckResult();        
        for (Object obj : values) {
            result.addResult(obj);
        }

        return result;
    }

    public StringCheckResult addResult(Object obj) {
        if(obj != null) {
            value += obj.hashCode();
            toStringValue += obj.toString() + ".";
        }
        return this;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof StringCheckResult
                && this.value.equals(((StringCheckResult)obj).value);
    }

    @Override
    public String toString() {
        return toStringValue;
    }
}
