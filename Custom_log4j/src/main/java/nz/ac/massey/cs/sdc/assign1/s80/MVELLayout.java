package nz.ac.massey.cs.sdc.assign1.s80;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.mvel2.templates.TemplateRuntime;

/**
 * @author atta
 * 
 * Custom layout for log4j
 *
 */
public class MVELLayout extends Layout {
	private String template = "NONE";

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String format(LoggingEvent event) {
		Map map = new HashMap();
		map.put("c", event.getLogger().toString());
		map.put("d", event.toString());
		map.put("m", event.getMessage());
		map.put("p", event.getLevel());
		map.put("t", event.getThreadName());
		map.put("n", "\n");
		return (String) TemplateRuntime.eval(template, map);

	}

	public void activateOptions() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean ignoresThrowable() {
		// TODO Auto-generated method stub
		return false;
	}
}