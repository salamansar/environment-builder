package org.envbuild.hibernate4;

/**
 *
 * @author kovlyashenko
 */
public interface MockSessionProvider {

    org.hibernate.Session getMockSession();
    
}
