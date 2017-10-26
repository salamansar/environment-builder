package org.envbuild.hibernate4;

import org.hibernate.Session;
import static org.mockito.Mockito.*;

/**
 * SessionFactory для использования компонентов, 
 * зависящих от реального подключения к БД, когда фактически его нет.
 * @author kovlyashenko
 */
public class MockSessionFactory extends SessionFactory {

    protected MockSessionFactory() {
    }

    public static MockSessionFactory create() {
        return create(new MockSessionProvider() {
            @Override
            public Session getMockSession() {
                return mock(Session.class);
            }
        });
    }
    
    public static MockSessionFactory create(MockSessionProvider sessionProvider) {
        MockSessionFactory factory = new MockSessionFactory();
        org.hibernate.SessionFactory mockFactory = mock(org.hibernate.SessionFactory.class);
        Session session = sessionProvider.getMockSession();
        when(mockFactory.getCurrentSession()).thenReturn(session);
        when(mockFactory.openSession()).thenReturn(session);
        factory.setSessionFactory(mockFactory);
        return factory;
    }
    
    
}
