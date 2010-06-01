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
 * Created at 2:20:29 PM, Apr 5, 2010
 */
package com.dmurph.mvc;

/**
 * This interface allows checking to see if an object is changed (is "dirty"),
 * and reverting changes back to when the object wasn't dirty.
 * @author Daniel Murphy
 */
public interface IDirtyable {
	
	/**
	 * @return if the object is dirty, if it was changed
	 * since the last time {@link #setDirty(boolean)} was called
	 * with false.
	 */
	public boolean isDirty();
	
	/**
	 * Sets if the model is dirty or not.  If called
	 * with false, the clean objects are cloned from the
	 * current working objects.  Equivalent of saving the
	 * model.  Basically, when {@link #revert()}
	 * is called it will revert to when setDirty(false) was called.
	 * @param argDirty true to make the object dirty, false to save
	 * 					the dirty values in the object
	 * @return the previous dirty value
	 */
	public boolean setDirty(boolean argDirty);
	
	/**
	 * Reverts the dirty changes. The model should
	 * not be dirty after this call.
	 * @return if the model was dirty previously
	 */
	public boolean revert();
}
