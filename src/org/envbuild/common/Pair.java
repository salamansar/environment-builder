package org.envbuild.common;

/**
 * Примитивный класс, хранящий в себе 2 параметра. По сути, копия {@link com.google.gwt.dev.json.Pair},
 * только еще добавлены гетеры для полей и дефолтный конструктор
 * @author vinakov
 */
public class Pair<A,B> {
    protected A a;
    protected B b;

    public Pair() {}

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
