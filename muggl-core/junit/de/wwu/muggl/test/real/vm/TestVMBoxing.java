package de.wwu.muggl.test.real.vm;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.wwu.muggl.configuration.Globals;
import de.wwu.muggl.vm.classfile.ClassFileException;
import de.wwu.muggl.vm.initialization.InitializationException;
import de.wwu.muggl.vm.loading.MugglClassLoader;

/**
 * 
 * @author Max Schulze
 *
 */
public class TestVMBoxing {
	MugglClassLoader classLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Globals.getInst().changeLogLevel(Level.ALL);
		Globals.getInst().parserLogger.setLevel(Level.WARN);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		classLoader = new MugglClassLoader(new String[] { "./", "./junit-res/" });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBugObjectReturn() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((Integer) 2,
				(Integer) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "putReturnObj",
						"(Ljava/lang/Object;)Ljava/lang/Object;", (Object[]) new Integer[] { 2 }));

	}

	@Test
	public final void testBugReturnCast2() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((Integer) 3,
				(Integer) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "putReturnObj2",
						"(Ljava/lang/Integer;)Ljava/lang/Integer;", (Object[]) new Integer[] { 3 }));

	}

	@Test
	public final void testBugReturnCast3() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((Integer) 4,
				(Integer) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "putReturnObj3",
						"(Ljava/lang/Integer;)Ljava/lang/Integer;", (Object[]) new Integer[] { 4 }));

	}

	@Test
	public final void testBugReturnCast4() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((Integer) 4,
				(Integer) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "putReturnObj4",
						"(Ljava/lang/Integer;)Ljava/lang/Integer;", (Object[]) new Integer[] { 4 }));

	}

	@Test
	public final void testBugIntegerBoxing() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((Integer) 8,
				(Integer) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "IntegerBoxing",
						"(Ljava/lang/Integer;)Ljava/lang/Integer;", (Object[]) new Integer[] { 4 }));
	}

	@Test
	public final void testBugBoxingBoolean() throws ClassFileException, InitializationException, InterruptedException {
		assertTrue("boxing boolean doesn't work",
				(Boolean) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "BooleanBoxing",
						"(Z)Ljava/lang/Boolean;", (Object[]) new Boolean[] { true }));

	}

	@Test
	public final void testBoolean1() throws ClassFileException, InitializationException, InterruptedException {
		assertTrue("boolean test",
				(Boolean) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "booleanTest2",
						"(Z)Z", (Object[]) new Boolean[] { true }));
	}

	@Test
	public final void testBugintNoBoxing() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals((int) 8,
				(int) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "intNoBoxing",
						"(I)I", (Object[]) new Integer[] { 4 }));

	}

	@Test
	public final void testPrinting() throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				de.wwu.muggl.binaryTestSuite.invokevirtual.MyType.class.getCanonicalName(), "testPrinting");

	}

	// === Boxing Conversion ===
	@Test
	public final void boxboolean() throws ClassFileException, InitializationException, InterruptedException {

		TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxboolean", "()Ljava/lang/String;",
				null);

	}

	@Test
	public final void boxBoolean() throws ClassFileException, InitializationException, InterruptedException {

		TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxBoolean",
				"(Ljava/lang/Boolean;)Ljava/lang/String;", (Object[]) new Boolean[] { true });

	}

	@Test
	public final void boxBoolean2() throws ClassFileException, InitializationException, InterruptedException {

		TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxBoolean2", "(Z)Z",
				(Object[]) new Boolean[] { true });

	}

	@Test
	public final void boxBooleanObj() throws ClassFileException, InitializationException, InterruptedException {

		TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxbooleanObj", "()Ljava/lang/String;",
				null);

	}

	@Test
	public final void boxPlaceholderBoolean() throws ClassFileException, InitializationException, InterruptedException {

		TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxPlaceholder",
				"(Ljava/lang/Boolean;)Ljava/lang/String;", (Object[]) new Boolean[] { true });

	}

	@Test
	public final void boxPlaceholderInt() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals("599",
				(String) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxPlaceholderint",
						"()Ljava/lang/String;", null));

	}

	@Test
	public final void boxintobject() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals("340", (String) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				de.wwu.muggl.binaryTestSuite.Boxing.class.getCanonicalName(), "boxint", "()Ljava/lang/String;", null));

	}

}