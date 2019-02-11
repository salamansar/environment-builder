# environment-builder
The library is made for helping of creating POJO-classes objects in unit tests cases and database preparation in integration tests cases.
It may simply generate new objects and fill them with random data with org.envbuild.generator.RandomGenerator.
Or even more, it may prepare objects for saving them into a database with org.envbuild.environment.DbEnvironmentBuilder and preconfigured objects processors org.envbuild.generator.processor.DomainPostProcessor.

## Including artifacts into project
In common case for Maven projects, you might use the following dependency:
```xml   
    <dependency>
        <groupId>com.github.salamansar.envbuild</groupId>
        <artifactId>environment-builder</artifactId>
        <version>0.8</version>
    </dependency>
```
If you are using Spring framework, it will be useful to use spring-version of the library with preconfigured context-configs:
```xml   
    <dependency>
        <groupId>com.github.salamansar.envbuild</groupId>
        <artifactId>environment-builder-spring</artifactId>
        <version>0.8</version>
    </dependency>
```
## RandomGenerator
For creation of an object, org.envbuild.generator.RandomGenerator is used. 
```java
    RandomGenerator generator = new RandomGenerator();
    SomeClass obj = generator.generate(SomeClass.class); 
```
There SomeClass is a POJO-class, which contains simple fields like String, Double, etc, with getters and setters. After generation, it will be filled with random values.
If a class contains not-simple fields with some other class, then this field will not be filled by default. For recursive generations such objects, you might turn on this feature with the special flag:
```java
	RandomGenerator generator = new RandomGenerator();
	SomeClass obj = generator.generate(SomeClass.class, true); //flag true enables recursive generation 
``` 
Instead, when you have an object, which must be set into some field in a class, then instead directly setting this object, you might pass them into generate method. The generator will find setters by passed objects types and set this property himself.
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
## DbEnvironmentBuilder
DbEnvironmentBuilder is the component for building a whole hierarchy of objects in a convenient way. Also it may persist created objects into a database.
### The Configuration of the builder

### Using of DbEnvironmentBuilder
For creation of an object, createObject method is used:
```java
	envBuilder.newBuild()
		.createObject(SomeClass.class).alias("someAlias");
``` 
The `alias` method is used for assign a tag to the created entity. Next, the entity might be received by using this tag.
```java
	SomeClass entity = envBuilder.getEnvironment().getByAlias("someAlias");
``` 
Suppose we have such hierarchy of two classes:
```java
	class Parent {
		...
	}
	class Child {
		private Parent parent;
		...
	}
``` 
Then in an IT-test we might create hierarchy with one parent-object and two children:
```java
	envBuilder.newBuild()
		.createObject(Parent.class).asParent()
			.createObject(Child.class)
			.createObject(Child.class)
``` 
Those children entities will be saved into a database with the link to the created Parent-object. The method `asParent` is used for marking created object to be a parent to any subsequently created object (of course if it contains a field with that object type).