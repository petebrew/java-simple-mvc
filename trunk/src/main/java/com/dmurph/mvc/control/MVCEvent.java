/**
 * Created at 2:22:05 AM, Mar 12, 2010
 */
package com.dmurph.mvc.control;

/**
 * Simple event.  To dispatch call {@link #dispatch()}.
 * Note that events dispatch globally, so make sure that
 * the keys you choose are unique.
 */
public class MVCEvent {
	public final String key;
	
	private volatile boolean propagate = true;
	
	public MVCEvent(final String argKey) {
		key = argKey;
	}

	@Override
	public String toString() {
		return super.toString() + "-" + key;
	}
	
	/**
	 * Stops the event from propagating to the rest of the listeners.  Listeners are stored
	 * as a stack, so newer listeners recieve events first.
	 */
	public void stopPropagation(){
		propagate = false;
	}
	
	protected boolean isPropagating(){
		return propagate;
	}
	
	/**
	 * Dispatches the event.  Events are dispatched globally, so make
	 * sure you're key is unique!
	 */
	public void dispatch(){
		MVC.dispatchEvent( this);
	}
}
