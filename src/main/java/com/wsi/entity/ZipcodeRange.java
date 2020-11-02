package com.wsi.entity;


/** 
* Entity class for ZipcodeRange.
* 
* @author  Ashish Porwal 
* @version 1.0 
* @since   11-01-2020 
*/
public class ZipcodeRange { 
	
	private int zipcodeStart;
	private int zipcodeEnd;

	
	public ZipcodeRange(int zipcodeStart, int zipcodeEnd) {
		this.zipcodeStart = zipcodeStart;
		this.zipcodeEnd = zipcodeEnd;
	}

	public int getZipcodeStart() {
		return zipcodeStart;
	}

	public void setZipcodeStart(int zipcodeStart) {
		this.zipcodeStart = zipcodeStart;
	}

	public int getZipcodeEnd() {
		return zipcodeEnd;
	}

	public void setZipcodeEnd(int zipcodeEnd) {
		this.zipcodeEnd = zipcodeEnd;
	}

	/**
	 * Overriding Object.toString for better presentation of ZipcodeRange.
	 * @param nothing. 
     * @return String.
	 */
	@Override 
	public String toString() { 
		  return "[" + zipcodeStart + " , " + zipcodeEnd + "]"; 
	}

	
	/**
	 * Overriding Object.equals. ZipcodeRange will be equal start and end are same.
	 * @param Object to compare with. 
     * @return boolean.
	 */
	@Override
    public boolean equals(Object obj) {
		ZipcodeRange o = (ZipcodeRange)obj;
		
		if (this.getZipcodeStart() == o.getZipcodeStart()  && 
				this.getZipcodeEnd() == o.getZipcodeEnd()) {
			return true;
		}else {
			return false;
		}
				

    }
	
	

}
