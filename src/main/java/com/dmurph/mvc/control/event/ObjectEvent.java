/**
 * 1:29:25 AM, Mar 25, 2010
 */
package com.dmurph.mvc.control.event;

import com.dmurph.mvc.control.MVCEvent;


/**
 * Simple Object event.
 * Note that events dispatch globally, so make sure that
 * the keys you choose are unique.
 * @author daniel
 */
public class ObjectEvent<K> extends MVCEvent {

	private final K object;
	/**
	 * @param argKey
	 */
	public ObjectEvent( String argKey, K argObject) {
		super( argKey);
		object = argObject;
	}
	
	public K getObject(){
		return object;
	}
}
