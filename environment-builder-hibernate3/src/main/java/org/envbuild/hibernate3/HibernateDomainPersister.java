package org.envbuild.hibernate3;

import org.envbuild.generator.processor.DomainPersister;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Salamansar
 */
@Component("hibernateDomainPersister")
public class HibernateDomainPersister implements DomainPersister {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void persistDomain(Object domain) {
        sessionFactory.getCurrentSession().save(domain);
        sessionFactory.getCurrentSession().refresh(domain);
    }
    
}
