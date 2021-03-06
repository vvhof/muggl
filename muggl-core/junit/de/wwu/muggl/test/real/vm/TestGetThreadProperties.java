package de.wwu.muggl.test.real.vm;

import static org.junit.Assert.*;

import java.lang.invoke.MethodType;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.wwu.muggl.configuration.Globals;
import de.wwu.muggl.test.TestSkeleton;
import de.wwu.muggl.vm.classfile.ClassFileException;
import de.wwu.muggl.vm.initialization.InitializationException;
import de.wwu.muggl.vm.loading.MugglClassLoader;

/**
 * 
 * @author Max Schulze
 *
 */
public class TestGetThreadProperties extends TestSkeleton {
	MugglClassLoader classLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!isForbiddenChangingLogLevel) {
			Globals.getInst().changeLogLevel(Level.DEBUG);
			Globals.getInst().parserLogger.setLevel(Level.ERROR);
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

	@Test // Known-to-fail: No threading in Muggl
	@Ignore
	public final void testGetThread() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals("main",
				TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.GetCurrentThread.class.getCanonicalName(),
						de.wwu.muggl.binaryTestSuite.GetCurrentThread.METHOD_getThread,
						MethodType.methodType(String.class), null));
	}

	@Test // Known-to-fail: No threading in Muggl
	@Ignore
	public final void testGetThreadGroup() throws ClassFileException, InitializationException, InterruptedException {
		assertEquals("main",
				TestVMNormalMethodRunnerHelper.runMethod(classLoader,
						de.wwu.muggl.binaryTestSuite.GetCurrentThread.class.getCanonicalName(),
						de.wwu.muggl.binaryTestSuite.GetCurrentThread.METHOD_getThreadGroup,
						MethodType.methodType(String.class), null));
	}

}
