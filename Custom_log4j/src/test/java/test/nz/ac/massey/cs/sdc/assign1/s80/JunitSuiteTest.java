package test.nz.ac.massey.cs.sdc.assign1.s80;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author atta 
 * Class to specify order of classes for test cases to follow while
 *         running
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({ AFirstTestToRunForMbeanCreationTest.class, AppenderAndLayoutsTest.class, StressTest.class })

public class JunitSuiteTest {
}