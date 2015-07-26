package org.envbuild.environment;

/**
 * @author kovlyashenko
 */
public class PrototypeAction {
    private PrototypeActionType actionType;
    private Object object;
    private Class<?> objectClass;

    public PrototypeAction(PrototypeActionType actionType) {
        this.actionType = actionType;
    }

    public PrototypeAction(PrototypeActionType actionType, Object object) {
        this.actionType = actionType;
        this.object = object;
    }

    public PrototypeAction(PrototypeActionType actionType, Class<?> objectClass) {
        this.actionType = actionType;
        this.objectClass = objectClass;
    }

    public PrototypeAction(PrototypeActionType actionType, Object object, Class<?> objectClass) {
        this.actionType = actionType;
        this.object = object;
        this.objectClass = objectClass;
    }

    public PrototypeActionType getActionType() {
        return actionType;
    }

    public void setActionType(PrototypeActionType actionType) {
        this.actionType = actionType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class<?> objectClass) {
        this.objectClass = objectClass;
    }


}
