package org.envbuild.environment.pattern;

/**
 * @author kovlyashenko
 */
public abstract class AbstractPattern implements DbEnvironmentPattern {
    protected String name;

    public AbstractPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
