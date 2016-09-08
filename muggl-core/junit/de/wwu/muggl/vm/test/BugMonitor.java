package de.wwu.muggl.vm.test;

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
public class BugMonitor {
	MugglClassLoader classLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Globals.getInst().changeLogLevel(Level.TRACE);
		Globals.getInst().parserLogger.setLevel(Level.ERROR);
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
	public final void testMonitorExecuteStaticFlag()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				"binary.openjdk.one.eight.zero.ninetyone.buginvokevirtual.SynchronizedExecute", "executeStaticFlag");

	}

	@Test
	public final void testMonitorExecuteStaticInstruction()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				"binary.openjdk.one.eight.zero.ninetyone.buginvokevirtual.SynchronizedExecute",
				"executeStaticInstruction");

	}

	@Test
	public final void testMonitorExecuteInstanceFlagWrapper()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				"binary.openjdk.one.eight.zero.ninetyone.buginvokevirtual.SynchronizedExecute",
				"executeInstanceFlagWrapper");

	}

	@Test
	public final void testMonitorExecuteInstanceInstructionWrapper()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				"binary.openjdk.one.eight.zero.ninetyone.buginvokevirtual.SynchronizedExecute",
				"executeInstanceInstructionWrapper");

	}

	@Test
	public final void testMonitorExecuteInstanceInstructionTwiceWrapper()
			throws ClassFileException, InitializationException, InterruptedException {
		TestVMNormalMethodRunnerHelper.runMethodNoArgVoid(classLoader,
				"binary.openjdk.one.eight.zero.ninetyone.buginvokevirtual.SynchronizedExecute",
				"executeInstanceInstructionTwiceWrapper");

	}

}