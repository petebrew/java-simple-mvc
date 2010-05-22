/**
 * Created on May 22, 2010, 3:50:08 AM
 */
package com.dmurph.mvc.model;

/**
 * @author Daniel
 *
 */
public abstract class AbstractDirtyableModel extends AbstractModel implements IDirtyable {

	private boolean dirty = false;

    /**
     * If the model is "dirty", or changed since last save.
     * @see com.dmurph.mvc.model.IDirtyable#isDirty()
     */
	@Override
	public boolean isDirty(){
		return dirty;
	}
	
	/**
	 * Call this every time a value is set.
	 * @see com.dmurph.mvc.model.IDirtyable#updateDirty(boolean)
	 */
	@Override
	public void updateDirty(boolean argIsDirty){
		dirty = dirty || argIsDirty;
	}
	
	/**
	 * @see com.dmurph.mvc.model.IDirtyable#setDirty(boolean)
	 */
	@Override
	public boolean setDirty(boolean argDirty){
		boolean oldDirty = dirty;
		if(dirty == argDirty){
			return dirty;
		}
		
		if(!dirty){
			save();
		}
		dirty = argDirty;
		return oldDirty;
	}
	
	/**
	 * Reverts to clean state, gets rid of changes.
	 * @see com.dmurph.mvc.model.IDirtyable#clean()
	 */
	@Override
	public boolean clean(){
		boolean oldDirty = dirty;
		revert();
		dirty = false;
		return oldDirty;
	}
	
	/**
	 * Revert the model to the clean values.
	 */
	protected abstract void revert();
	
	/**
	 * Save the clean values from the working/dirty values
	 */
	protected abstract void save();

}
