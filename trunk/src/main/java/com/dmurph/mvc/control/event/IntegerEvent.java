/**
 * 1:28:12 AM, Mar 25, 2010
 */
package com.dmurph.mvc.control.event;

import com.dmurph.mvc.control.MVCEvent;

/**
 * Simple integer event. Note that events dispatch globally, so make sure that
 * the keys you choose are unique.
 * @author daniel
 */
public class IntegerEvent extends MVCEvent{

	private final int integer;
	
	public IntegerEvent(String argKey, int argInt){
		super(argKey);
		integer = argInt;
	}
	
	public int getInteger(){
		return integer;
	}
}
