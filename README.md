# environment-builder
Library is made for preparation database for integration tests.
It may simple generate new objects and fill them with random data with org.envbuild.generator.RandomGenerator.
Or even more, it may prepare objects for saving them into database with org.envbuild.environment.DbEnvironmentBuilder and preconfigured objects processors org.envbuild.generator.processor.DomainPostProcessor.

The library works with latest hibernate 4.x and spring 4.1.x frameworks.
