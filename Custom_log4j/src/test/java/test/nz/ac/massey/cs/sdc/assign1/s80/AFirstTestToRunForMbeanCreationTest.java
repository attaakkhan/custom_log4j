package test.nz.ac.massey.cs.sdc.assign1.s80;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.junit.Test;

import nz.ac.massey.cs.sdc.assign1.s80.MemAppender;
import nz.ac.massey.cs.sdc.assign1.s80.My;

public class AFirstTestToRunForMbeanCreationTest {
	
	
	@Test
	public void testA(){
		
		new MemAppender();
		My cache = new My();
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name;
		try {
			name = new ObjectName("nz.ac.massey.cs.sdc.assign1.s80:type=MyMBean");
			mbs.registerMBean(cache, name);
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
}
