package org.envbuild.environment.pattern;

import org.envbuild.exception.PatternInitializationException;
import org.envbuild.environment.DbEnvironment;
import org.envbuild.environment.DbEnvironmentBuilder;
import org.envbuild.hibernate.SessionFactory;

/**
 * Адаптер для привязки к объектам и инициализации паттерна.
 * @author kovlyashenko
 */
public abstract class BindedPatternAdapter extends AbstractPattern implements BindedPattern {
    protected DbEnvironmentBuilder environmentBuilder;
    protected SessionFactory sessionFactory;
    
    protected BindedPatternAdapter(String name) {
        super(name);
    }   

    @Override
    public final void initializePattern() throws PatternInitializationException {
        if(!isBinded()) {
            throw new PatternInitializationException(this.getClass());
        }
        doInitialize();
    }

    @Override
    public final DbEnvironment createEnvironment() throws RuntimeException {
        initializePattern();
        doCreateDbEnvironment();
        return environmentBuilder.getEnvironment();
    }
    
    protected abstract void doCreateDbEnvironment() throws RuntimeException;
    
    /**
     * В этом методе проводим инициализацию паттерна
     * @throws su.ivt.test.environment.pattern.PatternInitializationException не удалось инициализировать
     */
    protected abstract void doInitialize() throws PatternInitializationException;

    @Override
    public boolean isBinded() {
        return environmentBuilder != null && sessionFactory != null;
    }
    
    public void setEnvironmentBuilder(DbEnvironmentBuilder environmentBuilder) {
        this.environmentBuilder = environmentBuilder;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
