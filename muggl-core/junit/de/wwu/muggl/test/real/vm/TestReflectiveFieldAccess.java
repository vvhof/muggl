package de.wwu.muggl.test.real.vm;

import static org.junit.Assert.assertEquals;

import java.lang.invoke.MethodType;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.wwu.muggl.binaryTestSuite.ReflectionField;
import de.wwu.muggl.configuration.Globals;
import de.wwu.muggl.test.TestSkeleton;
import de.wwu.muggl.vm.classfile.ClassFileException;
import de.wwu.muggl.vm.initialization.InitializationException;
import de.wwu.muggl.vm.initialization.Objectref;
import de.wwu.muggl.vm.loading.MugglClassLoader;

/**
 * 
 * @author Max Schulze
 *
 */
public class TestReflectiveFieldAccess extends TestSkeleton {
	MugglClassLoader classLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!isForbiddenChangingLogLevel) {
			Globals.getInst().changeLogLevel(Level.DEBUG);
			Globals.getInst().parserLogger.setLevel(Level.WARN);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		classLoader = new MugglClassLoader(mugglClassLoaderPaths);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testReflectiveFieldAccessIntPrim()
			throws ClassFileException, InitializationException, NoSuchMethodError, InterruptedException {

		assertEquals((int) 3,
				(int) TestVMNormalMethodRunnerHelper.runMethod(classLoader, ReflectionField.class.getCanonicalName(),
						ReflectionField.METHOD_test_getFieldValueReflectiveIntPrim, MethodType.methodType(int.class),
						(Object[]) new Object[0]));
	}

	@Test
	@Ignore // TODO: Add assertion
	public final void testReflectiveFieldAccessObj()
			throws ClassFileException, InitializationException, NoSuchMethodError, InterruptedException {
		Objectref res = (Objectref) TestVMNormalMethodRunnerHelper.runMethod(classLoader,
				ReflectionField.class.getCanonicalName(), ReflectionField.METHOD_test_getFieldValueReflectiveObj,
				MethodType.methodType(Object.class), (Object[]) new Object[0]);

		System.out.println(res);
	}

}
