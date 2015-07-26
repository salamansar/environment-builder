package org.envbuild.hibernate;

/**
 *
 * @author kovlyashenko
 */
public interface MockSessionProvider {

    org.hibernate.Session getMockSession();
    
}
