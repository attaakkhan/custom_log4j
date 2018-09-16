package nz.ac.massey.cs.sdc.assign1.s80;

import java.util.ArrayList;

import org.apache.log4j.spi.LoggingEvent;
/*
 * Create an MBean object for each instance of the MemAppender to add JMX monitoring to this object, the properties to be monitored are:
1. the log messages as array
2. the estimated size of the cached logs (total characters)
3. the number of logs that have been discarded
 */
public class My implements MyMBean {

	public synchronized long  getDiscardedLogs() {
		// TODO Auto-generated method stub
		return MemAppender.getInstance().getDiscardedLogCounts();
	}

	public synchronized long getCacheSize() {
		// TODO Auto-generated method stub
		
		 long size=0;
		 for(LoggingEvent i:MemAppender.getInstance().getCurrentLogs()){
			 
			 
			 size=size+((String)i.getMessage()).length();
		 }
		 
		 /*
		  * size of charactar of cache messages
		  */
		 return size;
	}

	public synchronized String getLogsMesseges() {
		
		// TODO Auto-generated method stub
		
		 ArrayList<String> list=new ArrayList<String>();
		 for(LoggingEvent i:MemAppender.getInstance().getCurrentLogs()){
			 
			 
			 list.add((String)i.getMessage());
		 }
		 return list.toString();
	}

}
