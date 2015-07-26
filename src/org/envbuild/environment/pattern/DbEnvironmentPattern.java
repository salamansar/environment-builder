package org.envbuild.environment.pattern;

import org.envbuild.environment.DbEnvironment;

/**
 * @author kovlyashenko
 */
public interface DbEnvironmentPattern {
    DbEnvironment createEnvironment() throws Exception;
    String getName();
}
