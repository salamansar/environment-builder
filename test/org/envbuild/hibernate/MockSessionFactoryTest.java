package org.envbuild.hibernate;

import org.envbuild.hibernate.SessionFactory;
import org.envbuild.hibernate.MockSessionFactory;
import org.envbuild.hibernate.MockSessionProvider;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;

/**
 *
 * @author akovlyashenko
 */
public class MockSessionFactoryTest {
    
    @Test
    public void testCreateDefaultFactory() {
        SessionFactory factory = MockSessionFactory.create();
        assertTrue(new MockUtil().isMock(factory.getCurrentSession()));
    }
    
    @Test
    public void testCreateFactoryWithCustomSessions() {
        final Session session = Mockito.mock(Session.class);
        SessionFactory factory = MockSessionFactory.create(new MockSessionProvider() {

            @Override
            public Session getMockSession() {
                return session;
            }
        });
        assertSame(session, factory.getCurrentSession());
        assertSame(session, factory.openSession());
    }
    
}
