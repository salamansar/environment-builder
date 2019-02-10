package org.envbuild.environment;

import org.envbuild.generator.RandomGenerator;
import org.envbuild.generator.processor.DomainPersister;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Salamansar
 */
public class DbEnvironmentBuilderTest {
	
	public static class Parent {
		Integer val;

		public Integer getVal() {
			return val;
		}

		public void setVal(Integer val) {
			this.val = val;
		}
	}
	
	public static class Child {
		private Integer val;
		private Parent parent;

		public Integer getVal() {
			return val;
		}

		public void setVal(Integer val) {
			this.val = val;
		}

		public Parent getParent() {
			return parent;
		}

		public void setParent(Parent parent) {
			this.parent = parent;
		}
		
	}
	
	private RandomGenerator randomGenerator = new RandomGenerator();
	private DomainPersister persister = mock(DomainPersister.class);
	private DbEnvironmentBuilder builder;

	@Before
	public void setUp() {
		builder = new DbEnvironmentBuilder(randomGenerator, persister);
	}

	@Test
	public void generateWithParent() {
		builder.newBuild()
				.createObject(Parent.class).asParent().alias("parent")
				.createObject(Child.class).alias("child");
		
		Parent parent = builder.getEnvironment().getByAlias("parent");
		assertNotNull(parent);
		assertNotNull(parent.val);
		
		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}
	
	@Test
	public void setCustomParent() {
		Parent parent = new Parent();
		builder.newBuild()
				.setParent(parent).alias("parent")
				.createObject(Child.class).alias("child");
		
		Parent parentInEnv = builder.getEnvironment().getByAlias("parent");
		assertNotNull(parentInEnv);
		assertSame(parent, parentInEnv);
		
		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}
	
	@Test
	public void generateWithParameter() {
		Parent parent = new Parent();
		builder.newBuild()
				.createObject(Child.class, parent).alias("child");
		
		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}
	
	@Test
	public void generateReplaceWithParameter() {
		Parent parent = new Parent();
		builder.newBuild()
				.createObject(Parent.class).asParent().alias("parent")
				.createObject(Child.class, parent).alias("child");
		
		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}
	
	@Test
	public void generateWithCustomizer() {
		final Parent parent = new Parent();
		ObjectCustomizer<Child> customizer = new ObjectCustomizer<Child>() {
			@Override
			public void customize(Child object) {
				object.parent = parent;
			}
		};
		builder.newBuild()
				.createObject(Child.class, customizer).alias("child");
		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}

	@Test
	public void generateReplaceWithCustomizer() {
		final Parent parent = new Parent();
		ObjectCustomizer<Child> customizer = new ObjectCustomizer<Child>() {
			@Override
			public void customize(Child object) {
				object.parent = parent;
			}
		};
		builder.newBuild()
				.createObject(Parent.class).asParent().alias("parent")
				.createObject(Child.class, customizer).alias("child");

		Child child = builder.getEnvironment().getByAlias("child");
		assertNotNull(child);
		assertNotNull(child.val);
		assertNotNull(child.parent);
		assertSame(parent, child.parent);
	}
}
