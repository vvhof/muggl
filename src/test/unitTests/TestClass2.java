package test.unitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class has been generated by Muggl for the automated testing of method
 * test.papers.Paper200809.binSearch(int v, int[] a).
 * Test cases have been computed using the symbolic execution of Muggl. Muggl
 * is a tool for the fully automated generation of test cases by analysing a
 * program's byte code. It aims at testing any possible flow through the program's
 * code rather than "guessing" required test cases, as a human would do.
 * Refer to http://www.wi.uni-muenster.de/pi/personal/majchrzak.php for more
 * information or contact the author at tim.majchrzak@wi.uni-muenster.de.
 * 
 * Executing the method main(null) will invoke JUnit (if it is in the class path).
 * The methods for setting up the test and for running the tests have also been
 * annotated.
 * 
 * Important settings for this run:
 * Search algorithm:            iterative deepening depth first
 * Time Limit:                  15 seconds
 * Maximum loop cycles to take: 200
 * Maximum instructions before
 * finding a new solution:     infinite
 *
 * Execution has been aborted before it was finished.
 * It might be possible to get more test cases by having less restrictive abortion
 * criteria.
 * 
 * The total number of solutions found was 1. After deleting redundancy and
 * removing unnecessary solutions, 1 distinct test cases were found.
 * It was tryed to eliminate more solutions based on their contribution to the
 * def-use chain and control graph edge coverage
but no further reduction was possible.
 * 
 * Covered def-use chains:		4 of 60
 * Covered control graph edges:	4 of 48
 * 
 * This file has been generated on Friday, 06 May, 2011 14:13.
 * 
 * @author Muggl 1.00 Alpha (unreleased)
 */
public class TestClass2 {
	// Fields for test parameters and expected return values.
	private int int0;
	private int[] array0 = null;

	/**
	 * Set up the unit test by initializing the fields to the desired values.
	 */
	@Before public void setUp() {
		this.int0 = 0;
	}

	/**
	 * Run the tests on test.papers.Paper200809.binSearch(int v, int[] a).
	 */
	@Test public void testIt() {
		try {
			test.papers.Paper200809.binSearch(this.int0, this.array0);
			fail("Expected a java.lang.NullPointerException to be thrown.");
		} catch (java.lang.NullPointerException e) {
			// Do nothing - this is what we expect to happen!
		}
	}

	/**
	 * Invoke JUnit to run the unit tests.
	 * @param args Command line arguments, which are ignored here. Just supply null.
	 */
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("test.unitTests.TestClass2");
	}

}