/**
 * Copyright (c) 2010 Daniel Murphy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * Created at Aug 4, 2010, 2:03:17 AM
 */
package com.dmurph.mvc.monitor;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.ObjectEvent;
import com.dmurph.mvc.monitor.EventMonitor.EventType;

/**
 * @author daniel
 *
 */
public class EventTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<LogEntry> events = new ArrayList<LogEntry>();
	private int nextMessageIndex = 0;
	private final int maxMessagesLogged;
	
	public EventTableModel(int argMaxMessagesLogged){
		maxMessagesLogged = argMaxMessagesLogged;
	}
	
	public void logEvent(MVCEvent argEvent, EventType argType){
		if(maxMessagesLogged == EventMonitor.LOG_ALL_MESSAGES){
			LogEntry entry = new LogEntry();
			entry.populate(argEvent, argType);
			events.add(entry);
			fireTableDataChanged();
			return;
		}
		if(events.size() == maxMessagesLogged){
			events.get(nextMessageIndex).populate(argEvent, argType);
			nextMessageIndex = (nextMessageIndex+1)%maxMessagesLogged;
			fireTableDataChanged();
			return;
		}
		LogEntry entry = new LogEntry();
		entry.populate(argEvent, argType);
		events.add(entry);
		fireTableDataChanged();
	}
	
	final String[] columns = {
			"Event Key", "Event Class", "Event Value", "Never Recieved", "Thread"
	};
	
	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int argColumn) {
		return columns[argColumn];
	}
	
	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columns.length;
	}
	
	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return events.size();
	}
	
	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int argRowIndex, int argColumnIndex) {
		if(maxMessagesLogged != EventMonitor.LOG_ALL_MESSAGES){
			argRowIndex += nextMessageIndex;
			argRowIndex %= maxMessagesLogged;
		}
		LogEntry entry = events.get(argRowIndex);
		switch(argColumnIndex){
			case 0:
				return entry.eventKey;
			case 1:
				return entry.eventClass;
			case 2:
				return (entry.eventValue != null)?entry.eventValue : "";
			case 3:
				return (entry.type == EventType.NO_LISTENERS)? "*" : null;
			case 4:
				return entry.threadName;
			default:
				return "?";
		}
	}
	
	private static class LogEntry{
		String eventClass;
		String eventKey;
		String eventValue;
		String threadName;
		EventType type;
		
		public void populate(MVCEvent argEvent, EventType argType){
			type = argType;
			eventClass = argEvent.getClass().getSimpleName();
			eventKey = argEvent.key;
			eventValue = (argEvent instanceof ObjectEvent<?>)? ((ObjectEvent<?>)argEvent).getValue().toString() : null;
			threadName = Thread.currentThread().getName();
		}
	}
}
