package org.envbuild.check;

import org.envbuild.common.Pair;

/**
 * Операция для формирования резултата при проверках пар {@link Pair}
 * @author kovlyashenko
 */
public class PairCheckOperation implements CheckOperation<Pair, PairCheckResult> {
    @Override
    public PairCheckResult check(Pair object) {
        return PairCheckResult.create(object);
    }
}
