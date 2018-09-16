package test.nz.ac.massey.cs.sdc.assign1.s80;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import nz.ac.massey.cs.sdc.assign1.s80.MVELLayout;
import nz.ac.massey.cs.sdc.assign1.s80.MemAppender;

/*
 * 4. write tests to stress-test your appender/layout by creating a large amount of
log statements [4 marks]
a. these tests are methods in a test class
test.nz.ac.massey.cs.sdc.assign1.s<student>.StressTest
b. use these tests to compare the performance between MemAppender using a
LinkedList, MemAppender using an ArrayList, ConsoleAppender and FileAppender - measure time and memory consumption (using JConsole or VisualVM or any profiler)
c. use these scripts to compare the performance between PatternLayout and MVELLayout
d. stress tests should test performance before and after maxSize has been reached
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StressTest {
	static Logger logger;

	@Test
	public void testAPerformanceMemAppenderWithLinkedList() {
		System.out.println("testAPerformanceMemAppenderWithLinkedList");

		/*
		 * Setting log4j MemAppender with max size 2000000
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,memAppender");
		map.put("log4j.appender.memAppender", "nz.ac.massey.cs.sdc.assign1.s80.MemAppender");
		map.put("log4j.appender.memAppender.maxSize", "2000000");

		Properties props = new Properties();
		props.putAll(map);
		/*
		 * Initializing log4j
		 */
		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		/*
		 * Setting Memappender list to an emptyList
		 */
		new MemAppender(new LinkedList<LoggingEvent>());
		/*
		 * getting logger
		 */
		logger = Logger.getLogger(StressTest.class);

		/*
		 * Generating logs
		 */
		for (int i = 0; i < 50000000; i++) {

			logger.info("Log " + 1);
			logger.error("ERROR " + i);
			logger.debug("Debug " + 1);

		}

	}

	@Test
	public void testAPerformanceMemAppenderArrayList() {
		System.out.println("testAPerformanceMemAppenderArrayList");

		/*
		 * Setting log4j MemAppender with max size 10000
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,memAppender");
		map.put("log4j.appender.memAppender", "nz.ac.massey.cs.sdc.assign1.s80.MemAppender");
		map.put("log4j.appender.memAppender.maxSize", "2000000");

		Properties props = new Properties();
		props.putAll(map);
		/*
		 * Initializing log4j
		 */
		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		/*
		 * Setting Memappender list to an emptyList
		 */
		new MemAppender(new ArrayList<LoggingEvent>());
		/*
		 * getting logger
		 */
		logger = Logger.getLogger(StressTest.class);

		/*
		 * Generating logs
		 */
		for (int i = 0; i < 50000000; i++) {

			logger.info("Log " + 1);
			logger.error("ERROR " + i);
			logger.debug("Debug " + 1);

		}

	}

	@Test
	public void testdPerformanceConsoleAppender() {
		System.out.println("testCPerformanceConsoleAppender");

		/*
		 * Setting log4j MemAppender with max size 10000
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,stdout");
		map.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");

		Properties props = new Properties();
		props.putAll(map);
		/*
		 * Initializing log4j
		 */
		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		/*
		 * Setting Memappender list to an emptyList
		 */
		// new MemAppender(new ArrayList<LoggingEvent>());
		/*
		 * getting logger
		 */
		logger = Logger.getLogger(StressTest.class);

		/*
		 * Generating logs
		 */
		for (int i = 0; i < 100000000; i++) {

			logger.info("Log " + 1);
			logger.error("ERROR " + i);
			logger.debug("Debug " + 1);

		}

	}

	@Test
	public void testDPerformanceFileAppender() {
		System.out.println("testCPerformanceFileAppender");

		/*
		 * Setting log4j MemAppender with max size 10000
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,stdout");
		map.put("log4j.appender.stdout", "org.apache.log4j.FileAppender");
		// map.put("log4j.appender.stdout.Target", "System.out");

		Properties props = new Properties();
		props.putAll(map);
		/*
		 * Initializing log4j
		 */
		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		/*
		 * Setting Memappender list to an emptyList
		 */
		// new MemAppender(new ArrayList<LoggingEvent>());
		/*
		 * getting logger
		 */
		logger = Logger.getLogger(StressTest.class);

		/*
		 * Generating logs
		 */
		for (int i = 0; i < 100000000; i++) {

			logger.info("Log " + 1);
			logger.error("ERROR " + i);
			logger.debug("Debug " + 1);

		}

	}

	@Test
	public void testEPerformanceMvellLayout() {

		System.out.println("testEPerformanceMvellLayout");

		List<LoggingEvent> list = new ArrayList<LoggingEvent>();
		MVELLayout layout;
		layout = new MVELLayout();
		layout.setTemplate("@{m}");
		logger = Logger.getLogger(StressTest.class);
		for (int i = 0; i < 10000000; i++) {
			layout.format(
					new LoggingEvent("a", logger, 0, logger.getLevel(), "Msg " + i, "TEST", null, null, null, null));
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFPerformancePatternLayout() {

		System.out.println("testFPerformancePatternLayout");
		List<LoggingEvent> list = new ArrayList<LoggingEvent>();
		PatternLayout layout;
		layout = new PatternLayout();
		layout.setConversionPattern("%m%n");
		logger = Logger.getLogger(StressTest.class);
		for (int i = 0; i < 100000000; i++) {
			layout.format(
					new LoggingEvent("a", logger, 0, logger.getLevel(), "Msg " + i, "TEST", null, null, null, null));
		}

	}

	@Test
	public void testGPerformanceMemAppenderWhenMaxsizeReached() {
		System.out.println("testAPerformanceMemAppenderWithLinkedList");

		/*
		 * Setting log4j MemAppender with max size 10000
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("log4j.rootLogger", "DEBUG,memAppender");
		map.put("log4j.appender.memAppender", "nz.ac.massey.cs.sdc.assign1.s80.MemAppender");
		map.put("log4j.appender.memAppender.maxSize", "2000000");

		Properties props = new Properties();
		props.putAll(map);
		/*
		 * Initializing log4j
		 */
		org.apache.log4j.LogManager.resetConfiguration();
		org.apache.log4j.PropertyConfigurator.configure(props);
		/*
		 * Setting Memappender list to an emptyList
		 */
		new MemAppender(new LinkedList<LoggingEvent>());
		/*
		 * getting logger
		 */
		logger = Logger.getLogger(StressTest.class);

		/*
		 * Generating logs
		 */
		for (int i = 0; i < 3000000; i++) {

			logger.info("Log " + 1);

		}

	}

}
