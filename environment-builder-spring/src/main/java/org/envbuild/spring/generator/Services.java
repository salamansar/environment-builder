package org.envbuild.spring.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author kovlyashenko
 */
public interface Services {

    @Qualifier("simpleGenerationService")
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface SimpleService {
        
    }
    
    @Qualifier("domainGenerationService")
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface DomainService {
        
    }
    
}
