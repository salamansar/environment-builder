package org.envbuild.spring;

import org.envbuild.generator.GenerationService;
import org.envbuild.spring.config.SimpleGeneratorConfig;
import org.envbuild.spring.generator.Services;
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
@ContextConfiguration(classes = SimpleGeneratorConfig.class)
public class SimpleConfigTest {
    
    @Autowired
    @Services.SimpleService
    private GenerationService generator;
    
    @Test
    public void generateObjects() {
        Category category = generator.generate(Category.class);
        
        assertNotNull(category);
        assertNotNull(category.getId());
        assertNotNull(category.getName());
        
        Payment payment = generator.generate(Payment.class, category);
        
        assertNotNull(payment);
        assertNotNull(payment.getId());
        assertNotNull(payment.getValue());
        assertSame(category, payment.getCategory());
    }
    
}
