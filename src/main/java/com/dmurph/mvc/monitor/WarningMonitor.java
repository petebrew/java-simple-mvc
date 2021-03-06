/**
 * Created on Jun 22, 2010, 1:53:26 AM
 */
package com.dmurph.mvc.monitor;

import com.dmurph.mvc.I18n;
import com.dmurph.mvc.IGlobalEventMonitor;
import com.dmurph.mvc.MVCEvent;

/**
 * Displays warnings to std out.
 */
public class WarningMonitor implements IGlobalEventMonitor {
	
	private IGlobalEventMonitor monitor;
	
	public WarningMonitor(){
		this(null);
	}
	public WarningMonitor(IGlobalEventMonitor argMonitor){
		monitor = argMonitor;
	}
	
	/**
	 * @see com.dmurph.mvc.IGlobalEventMonitor#afterDispatch(com.dmurph.mvc.MVCEvent)
	 */
	public void afterDispatch(MVCEvent argEvent) {
		if(monitor != null){
			monitor.afterDispatch(argEvent);
		}
	}
	
	/**
	 * @see com.dmurph.mvc.IGlobalEventMonitor#beforeDispatch(com.dmurph.mvc.MVCEvent)
	 */
	public void beforeDispatch(MVCEvent argEvent) {
		if(monitor != null){
			monitor.beforeDispatch(argEvent);
		}
	}
	
	/**
	 * Warns to the console that an event has no listeners
	 * @see com.dmurph.mvc.IGlobalEventMonitor#noListeners(com.dmurph.mvc.MVCEvent)
	 */
	public void noListeners(MVCEvent argEvent) {
		if(monitor != null){
			monitor.noListeners(argEvent);
		}
		System.out.println(I18n.getText("monitor.noListeners", argEvent.key));
	}
	
	/**
	 * @see com.dmurph.mvc.IGlobalEventMonitor#exceptionThrown(com.dmurph.mvc.MVCEvent, java.lang.Exception)
	 */
	public void exceptionThrown(MVCEvent argEvent, Exception argException) {
		if(monitor != null){
			monitor.exceptionThrown(argEvent, argException);
		}
		// TODO locale
		System.err.println(I18n.getText("monitor.exception", argEvent.key)+" "+argException);
		argException.printStackTrace(System.err);
	}
}
