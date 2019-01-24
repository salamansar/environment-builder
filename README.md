# environment-builder
Library is made for preparation database for integration tests.
It may simple generate new objects and fill them with random data with org.envbuild.generator.RandomGenerator.
Or even more, it may prepare objects for saving them into database with org.envbuild.environment.DbEnvironmentBuilder and preconfigured objects processors org.envbuild.generator.processor.DomainPostProcessor.

## Including artifacts into project
In common case for maven projects you might use following dependency:
```xml   
    <dependency>
        <groupId>com.github.salamansar.envbuild</groupId>
        <artifactId>environment-builder</artifactId>
        <version>0.8</version>
    </dependency>
```
If you are using the spring framwork, it will be useful to use spring-version of library with preconfigured context-configs:
```xml   
    <dependency>
        <groupId>com.github.salamansar.envbuild</groupId>
        <artifactId>environment-builder-spring</artifactId>
        <version>0.8</version>
    </dependency>
```
## Using of RandomGenerator
For creation of object org.envbuild.generator.RandomGenerator is used. 
```java
    RandomGenerator generator = new RandomGenerator();
    SomeClass obj = generator.generate(SomeClass.class); 
```
There SomeClass is a POJO-class, which contains simple fields like String, Double, etc with getters and setters. After generation it will be filled with random values.
If class contains not-simple fields with some other class, then this filed will not be filled be default. For recursive generations such objects you might turn on this feature with special flag:
```java
	RandomGenerator generator = new RandomGenerator();
	SomeClass obj = generator.generate(SomeClass.class, true); //flag true enables recursive generation 
``` 
Instead that when you have object, which must be set into some field in the class, then instead directly setting this object, you might pass them into generate method. Generator will find setters by passed objects type and set this property himself.
Instead
```java
	SomeOtherClass otherObj = ...
	RandomGenerator generator = new RandomGenerator();
	SomeClass obj = generator.generate(SomeClass.class);
	obj.setOther(otherObject);
``` 
You might use
```java
	SomeOtherClass otherObj = ...
	RandomGenerator generator = new RandomGenerator();
	SomeClass obj = generator.generate(SomeClass.class, otherObj); //property other will be set by generator
``` 
