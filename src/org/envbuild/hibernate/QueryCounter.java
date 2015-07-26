package org.envbuild.hibernate;

import javax.annotation.PostConstruct;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Компонент для подсчета количества запросов к базе.
 * Используется в тех местах, где их количество не должно выходить за пределы дозволенного.
 * Запускается непосредственно перед тем местом, которое должно быть протестировано (вызов метода сервиса, дао и т.п.)
 * и оканчивается сразу после него.
 * @author kovlyashenko
 */
@Component
public class QueryCounter {
    @Autowired
    private SessionFactory sessionFactory;
    private Statistics statistics;
    
    /**
     * Начать запись статистики. После этого все запросы к базе будут подсчитываться.
     */
    public void start() {
        reset();
        statistics.setStatisticsEnabled(true);
    }
    
    /**
     * Остановить подсчет. 
     * @return this
     */
    public QueryCounter stop() {
        statistics.setStatisticsEnabled(false);
        return this;
    }
    
    /**
     * Сбросить счетчики.
     * @return this
     */
    public QueryCounter reset() {
        statistics.clear();
        return this;
    }
    
    /**
     * @return количество запросов к базе, подсчитанных в результате работы компонента
     */
    public long getResultCount() {
        return statistics.getQueryExecutionCount() 
                + statistics.getEntityFetchCount() 
                + statistics.getCollectionLoadCount();
    }
    
    @PostConstruct
    protected void initialize() {
        statistics = sessionFactory.getStatistics();
    }
}
