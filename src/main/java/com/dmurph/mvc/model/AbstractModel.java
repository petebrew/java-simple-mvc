package com.dmurph.mvc.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * <p>Abstract model class, used for storing data and throwing {@link PropertyChangeEvent}s when values are changed.
 * Extending classes should call {@link #firePropertyChange(String, Object, Object)} for this to work.
 * For storing arrays of values, look at {@link CloneableArrayList} for easy cloning.</p>
 * <p>For models that need to keep track of original values versus new values (like a dirtyable
 * model that revert to original values, used for knowing if something needs saving, etc), then look at 
 * {@link AbstractDirtyableModel}.</p>
 * 
 * @see PropertyChangeEvent
 * @see PropertyChangeListener
 * @see AbstractDirtyableModel
 * @author daniel
 */
public abstract class AbstractModel implements IDirtyable, ICloneable{
	protected final PropertyChangeSupport propertyChangeSupport;
	
    public AbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Adds a property change listener to this model
     * @param argListener
     */
    public void addPropertyChangeListener(PropertyChangeListener argListener) {
        propertyChangeSupport.addPropertyChangeListener(argListener);
    }

    /**
     * Removes a property change listener to this model
     * @param argListener
     */
    public void removePropertyChangeListener(PropertyChangeListener argListener) {
        propertyChangeSupport.removePropertyChangeListener(argListener);
    }

    /**
     * Fires a property change event.  If the argOldValue == argNewValue
     * or argOldValue.equals( argNewValue) then no event is thrown.
     * @param argPropertyName property name, should match the get and set methods for property name
     * @param argOldValue
     * @param argNewValue
     */
    protected void firePropertyChange(String argPropertyName, Object argOldValue, Object argNewValue) {
    	if(argOldValue.equals(argNewValue)){
    		return;
    	}
    	// this handles the rest internally
        propertyChangeSupport.firePropertyChange(argPropertyName, argOldValue, argNewValue);
    }
	
	/**
	 * @see ICloneable#clone()
	 */
	@Override
	public abstract ICloneable clone();
}
