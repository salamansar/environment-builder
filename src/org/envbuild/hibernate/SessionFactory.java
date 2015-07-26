package org.envbuild.hibernate;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kovlyashenko
 */
@Component("customSessionFactory")
public class SessionFactory {
    private Session customSession;
    
    private org.hibernate.SessionFactory sessionFactory;

    public Session getCurrentSession() {
        if(customSession == null) {
            return sessionFactory.getCurrentSession();
        } else {
            return customSession;
        }
    }

    public Statistics getStatistics() {
        return sessionFactory.getStatistics();
    }

    public void setSession(Session session) {
        customSession = session;
    }

    public void openCurrentSession() {
        customSession = openSession();
    }
    
    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void closeCurrentSession() {
        customSession.close();
        customSession = null;
    }
    
    public void reopenCurrentSession() {
        closeCurrentSession();
        openCurrentSession();
    }

    @Autowired
    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
