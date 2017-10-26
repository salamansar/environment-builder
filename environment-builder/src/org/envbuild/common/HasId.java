package org.envbuild.common;

/**
 * Интерфейс объекта, имеющего идишник какого-либо типа.
 * 
 * @param <IDType> тип идишника
 * @author kovlyashenko
 */
public interface HasId<IDType> {
    IDType getId();
    void setId(IDType id);
}
