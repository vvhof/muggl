package de.wwu.muggl.test.real.instructions;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.wwu.muggl.configuration.Globals;
import de.wwu.muggl.test.TestSkeleton;
import de.wwu.muggl.test.real.vm.TestVMNormalMethodRunnerHelper;
import de.wwu.muggl.vm.classfile.ClassFileException;
import de.wwu.muggl.vm.initialization.InitializationException;
import de.wwu.muggl.vm.loading.MugglClassLoader;

/**
 * 
 * @author Max Schulze
 *
 */
public class TestInvokestaticHashTable extends TestSkeleton {
	MugglClassLoader classLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!isForbiddenChangingLogLevel) {
			Globals.getInst().changeLogLevel(Level.TRACE);
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
	public final void testInvokeStaticHashMapPut()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				de.wwu.muggl.binaryTestSuite.invokestatic.HashTablePut.class.getCanonicalName(), "executeHashMapPut");

	}

	@Test
	public final void testInvokeStaticHashTablePut()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				de.wwu.muggl.binaryTestSuite.invokestatic.HashTablePut.class.getCanonicalName(), "executeHashTablePut");

	}

	@Test
	public final void testInvokeStaticEnumMap()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				de.wwu.muggl.binaryTestSuite.invokestatic.HashTablePut.class.getCanonicalName(), "executeEnumMap");

	}

	@Test
	public final void testInvokeStaticHashMapReflection()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				de.wwu.muggl.binaryTestSuite.invokestatic.HashTablePut.class.getCanonicalName(),
				"executeHashMapReflection");

	}

}
