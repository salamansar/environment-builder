package org.envbuild.check;

/**
 * Операция проверки объекта
 * @param <OBJ> тип объекта
 * @param <RES> тип результата
 * @author kovlyashenko
 */
public interface CheckOperation<OBJ, RES> {
    RES check(OBJ object);
}
