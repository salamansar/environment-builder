package org.envbuild.generator;

import org.envbuild.generator.RandomGenerator;
import org.envbuild.generator.GenerationService;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.envbuild.check.CheckCommonUtils;
import org.envbuild.check.CheckListConditionHelper;
import org.envbuild.check.NotNullOperation;

/**
 *
 * @author akovlyashenko
 */
public class GenerationServiceTest {
    private GenerationService service = new GenerationService();
    
    
    public GenerationServiceTest() {
        RandomGenerator generator = new RandomGenerator();
        service.setGenerator(generator);
    }

    @Test
    public void testGenerateIntegersList() {
        List<Integer> generated = service.generateList(Integer.class, 10);
        
        CheckCommonUtils.checkList(generated, 10);
        CheckListConditionHelper.get(new NotNullOperation()).check(generated);
    }
    
}
