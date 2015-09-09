# environment-builder
Library is made for preparation database for integration tests.
It may simple generate new objects and fill them with random data with org.envbuild.generator.RandomGenerator.
Or even more, it may prepare objects for saving them into database with org.envbuild.environment.DbEnvironmentBuilder and preconfigured objects processors org.envbuild.generator.processor.DomainPostProcessor.

The library works with latest hibernate 4.x and spring 4.1.x frameworks.

## Instruction for sign and build project with PGP signature ##
1. Go to https://www.gnupg.org/ and install GnuPG package following instructions from website for your OS.

2. Follow instructions on http://central.sonatype.org/pages/working-with-pgp-signatures.html and generate your key pair.
Also if you already have key pair, you might put them in .gnupg directory under your home directory.
Note: if you use windows (especially win8), then you might use another program from http://www.gpg4win.org if you have troubles or errors.

3. Don't forget distribute your public key as shown in instructions above.

4. If you use NetBeans and it was run at moment of key creation, then restart IDE.

5. Now you can build and deploy project.