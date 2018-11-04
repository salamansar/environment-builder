package org.envbuild.spring;

import org.envbuild.environment.DbEnvironment;
import org.envbuild.environment.DbEnvironmentBuilder;
import org.envbuild.generator.processor.DomainPersister;
import org.envbuild.generator.processor.DomainPostProcessor;
import org.envbuild.spring.config.PersistOrientedGeneratorConfig;
import org.envbuild.spring.test.Category;
import org.envbuild.spring.test.Payment;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Salamansar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ExtendedPersistOrientedConfigTest {
    
    @Configuration
    @Import(PersistOrientedGeneratorConfig.class)
    public static class ContextConfiguration {
        @Bean
        public DomainPersister customPersister() {
            return mock(DomainPersister.class);
        }
        
        @Bean
        @Qualifier("categoryProcessor")
        public DomainPostProcessor<Category> categoryPostProcessor() {
            return spy(new DomainPostProcessor<Category>() {
                @Override
                public Class<Category> getDomainClass() {
                    return Category.class;
                }

                @Override
                public void processDomain(Category domain) throws Exception {
                }
            });
        }
        
        @Bean
        @Qualifier("paymentProcessor")
        public DomainPostProcessor<Payment> paymentPostProcessor() {
            return spy(new DomainPostProcessor<Payment>() {
                @Override
                public Class<Payment> getDomainClass() {
                    return Payment.class;
                }

                @Override
                public void processDomain(Payment domain) throws Exception {
                }
            });
        }
        
    }
    
    @Autowired
    private DbEnvironmentBuilder envBuilder;
    @Autowired
    private DomainPersister domainPersister;
    @Autowired
    @Qualifier("categoryProcessor")
    private DomainPostProcessor<Category> categoryProcessor;
    @Autowired
    @Qualifier("paymentProcessor")
    private DomainPostProcessor<Payment> paymentProcessor;
    
    @Test
    public void generateEnvironment() throws Exception {
        DbEnvironment environment = envBuilder.newBuild()
                .createObject(Category.class).alias("cat1").asParent()
                    .createObject(Payment.class).alias("pay1")
                    .createObject(Payment.class).alias("pay2")
                .getEnvironment();

        Category cat1 = environment.getByAlias("cat1");
        checkCategory(cat1);

        Payment payment1 = environment.getByAlias("pay1");
        checkPayment(payment1, cat1);

        Payment payment2 = environment.getByAlias("pay2");
        checkPayment(payment2, cat1);

        verify(domainPersister).persistDomain(same(cat1));
        verify(domainPersister).persistDomain(same(payment1));
        verify(domainPersister).persistDomain(same(payment2));
        
        verify(categoryProcessor).processDomain(same(cat1));
        verify(paymentProcessor).processDomain(same(payment1));
        verify(paymentProcessor).processDomain(same(payment2));
    }

    private void checkCategory(Category cat1) {
        assertNotNull(cat1);
        assertNotNull(cat1.getId());
        assertNotNull(cat1.getName());
    }

    private void checkPayment(Payment payment1, Category cat1) {
        assertNotNull(payment1);
        assertNotNull(payment1.getId());
        assertNotNull(payment1.getValue());
        assertSame(payment1.getCategory(), cat1);
    }
    
}
