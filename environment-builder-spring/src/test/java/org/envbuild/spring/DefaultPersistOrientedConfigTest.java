package org.envbuild.spring;

import org.envbuild.environment.DbEnvironment;
import org.envbuild.environment.DbEnvironmentBuilder;
import org.envbuild.spring.config.PersistOrientedGeneratorConfig;
import org.envbuild.spring.test.Category;
import org.envbuild.spring.test.Payment;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Salamansar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistOrientedGeneratorConfig.class)
public class DefaultPersistOrientedConfigTest {
    
    @Autowired
    private DbEnvironmentBuilder envBuilder;
    
    @Test
    public void generateEnvironment() {
        DbEnvironment environment = envBuilder.newBuild()
                .createObject(Category.class).alias("cat1").asParent()
                .createObject(Payment.class).alias("pay1")
                .createObject(Payment.class).alias("pay2")
                .createObject(Category.class).alias("cat2").asParent()
                .createObject(Payment.class).alias("pay3")
                .createObject(Payment.class).alias("pay4")
                .clearParents()
                .createObject(Payment.class).alias("pay5")
                .getEnvironment();

        Category cat1 = environment.getByAlias("cat1");

        assertNotNull(cat1);
        assertNotNull(cat1.getId());
        assertNotNull(cat1.getName());

        Category cat2 = environment.getByAlias("cat2");

        assertNotNull(cat2);
        assertNotNull(cat2.getId());
        assertNotNull(cat2.getName());

        Payment payment1 = environment.getByAlias("pay1");
        checkPayment(payment1, cat1);
        Payment payment2 = environment.getByAlias("pay2");
        checkPayment(payment2, cat1);
        Payment payment3 = environment.getByAlias("pay3");
        checkPayment(payment3, cat2);
        Payment payment4 = environment.getByAlias("pay4");
        checkPayment(payment4, cat2);
        Payment payment5 = environment.getByAlias("pay5");
        assertNotNull(payment5);
        assertNotNull(payment5.getId());
        assertNotNull(payment5.getValue());
        assertNull(payment5.getCategory());

    }

    private void checkPayment(Payment payment1, Category cat1) {
        assertNotNull(payment1);
        assertNotNull(payment1.getId());
        assertNotNull(payment1.getValue());
        assertSame(payment1.getCategory(), cat1);
    }
    
}
