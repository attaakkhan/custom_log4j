package test.nz.ac.massey.cs.sdc.assign1.s80;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import nz.ac.massey.cs.sdc.assign1.s80.MVELLayout;
import nz.ac.massey.cs.sdc.assign1.s80.MemAppender;

/*
 * write tests that test your appender and layout in combination with different
loggers, levels and appenders [5 marks]
a. use JUnit4 for testing
￼￼￼
b. aim for good test coverage and precise asserts
c. tests should be in the package
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppenderAndLayoutsTest {
	static Logger logger;

	@BeforeClass
	public static void testBeforeClass() {

		System.out.println("testBeforeClass");
		/*
		 * reseting appender
		 */
		MemAppender.getInstance().setDiscardedLogCounts(0);
		new MemAppender();
		/*
		 * Setting log4j MemAppender && MVELLayout
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,memAppender");
		map.put("log4j.appender.memAppender", "nz.ac.massey.cs.sdc.assign1.s80.MemAppender");
		map.put("log4j.appender.memAppender.maxSize", "10");
		MVELLayout layout=new MVELLayout();
		layout.setTemplate("Message:@{m} Level:@{p} @{n}Thread:@{t}");
		MemAppender.getInstance().setLayout(layout);


		Properties props = new Properties();
		props.putAll(map);

		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		logger = Logger.getLogger(AppenderAndLayoutsTest.class);
		/*
		 * logs generation
		 */
		for (int i = 0; i < 10; i++) {

			logger.info("Log " + 1);
			logger.error("ERROR " + i);
			logger.debug("Debug " + 1);

		}
	}

	@Test
	public void testAMemAppenderDiscardedLogs() {
		System.out.println("testAMemAppenderDiscardedLogs");
		// testing discarded logs
		assertEquals(MemAppender.getInstance().getDiscardedLogCounts(), 30);

	}

	@Test
	public void testBMemAppenderCuurentLogs() {
		System.out.println("testBMemAppenderCuurentLogs");
		// testing current logs
		assertEquals(MemAppender.getInstance().getCurrentLogs().size(), 0);

	}

	@Test
	public void testCMemAppenderCuurentLogsNoModifible() {
		// testing current logs
		System.out.println("testCMemAppenderCuurentLogsNoModifible");

		try {
			MemAppender.getInstance().getCurrentLogs().remove(0);
			org.junit.Assert.fail();
		} catch (Exception e) {

		}

	}

	@Test
	public void testDMvelLayoutFormate() {
		System.out.println("testDMvelLayoutFormate");
		logger.info("Log " + 1);
		
		assertEquals(MemAppender.getInstance().getLayout().format(MemAppender.getInstance().getCurrentLogs().get(0)),
				"Message:Log 1 Level:INFO \nThread:main");

	}

	@Test
	public void testEMvelLayoutWithConsoleappender() {
		System.out.println("testEMvelLayoutWithConsoleappender");
		Map<String, String> map = new HashMap<String, String>();
		map.put("-Dlog4j.defaultInitOverride", "true");
		map.put("log4j.rootLogger", "DEBUG,stdout");
		map.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		map.put("log4j.appender.stdout.Target", "System.out");

		map.put("log4j.appender.stdout.layout", "nz.ac.massey.cs.sdc.assign1.s80.MVELLayout");
		map.put("log4j.appender.stdout.layout.template", "Message:@{m} Level:@{p} @{n}Thread:@{t}");
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);
		Properties props = new Properties();
		props.putAll(map);

		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);

		logger = Logger.getLogger(AppenderAndLayoutsTest.class);

		logger.info("Log 1");
		final String consoleOutput = baos.toString();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

		assertEquals(consoleOutput, "Message:Log 1 Level:INFO \nThread:main");
	}

}
