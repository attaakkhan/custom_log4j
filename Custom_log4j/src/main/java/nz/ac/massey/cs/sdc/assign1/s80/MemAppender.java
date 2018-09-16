package nz.ac.massey.cs.sdc.assign1.s80;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;


/**
 * @author atta
 * Custom appender for log4j
 *
 */
public class MemAppender extends AppenderSkeleton {

	static MemAppender INSTANCE;// = new MemAppender();
	List<LoggingEvent> list = null;// = new ArrayList<LoggingEvent>();
	int maxSize = 10;
	long discardedLogCounts = 0;

	public MemAppender(List<LoggingEvent> list) {
		if (INSTANCE == null)
			INSTANCE = this;
		INSTANCE.list = list;

	}

	public MemAppender() {
		if (INSTANCE == null) {
			INSTANCE = this;
			INSTANCE.list = new ArrayList<LoggingEvent>();
		}

	}

	public static MemAppender getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MemAppender(new ArrayList<LoggingEvent>());
		return INSTANCE;
	}

	public List<LoggingEvent> getCurrentLogs() {

		return Collections.unmodifiableList(list);

	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public long getDiscardedLogCounts() {
		return discardedLogCounts;
	}

	public void setDiscardedLogCounts(long discardedLogCounts) {
		this.discardedLogCounts = discardedLogCounts;
	}

	public void close() {
		// TODO Auto-generated method stub

	}

	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		try {
			getInstance().list.add(event);
			if (getInstance().getMaxSize() == getInstance().list.size()) {
				getInstance().setDiscardedLogCounts(getInstance().getDiscardedLogCounts() + maxSize);
				getInstance().list.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
