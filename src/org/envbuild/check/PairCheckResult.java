package org.envbuild.check;

import org.envbuild.common.Pair;

/**
 * Результат проверки пары {@link org.envbuild.common.Pair}, которая часто возникает,
 * например, при проверке количества детей для пачки объектов
 * @author kovlyashenko
 */
public class PairCheckResult {
    private StringCheckResult result;

    public PairCheckResult(Pair pair) {
        this(pair.getA(), pair.getB());
    }

    public PairCheckResult(Object A, Object B) {
        result = StringCheckResult.create(A, B);
    }

    public static PairCheckResult create(Pair pair) {
        return new PairCheckResult(pair);
    }

    public static PairCheckResult create(Object A, Object B) {
        return new PairCheckResult(A, B);
    }

    @Override
    public int hashCode() {
        return result.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PairCheckResult
                && result.equals(((PairCheckResult)obj).result);
    }
}
