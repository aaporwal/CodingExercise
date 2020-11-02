package com.wsi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.wsi.entity.ZipcodeRange;

/** 
* ZipcodeService class provide function on ZipcodeRange.  
* 
* @author  Ashish Porwal 
* @version 1.0 
* @since   11-01-2020 
*/
public class ZipcodeService {
	
	private static final int MIN_ZIP_START = 10000;
	private static final int MAX_ZIP_END = 99999;


	/**
	 * This method is calculating the list of minimum ranges, from given list of ranges.  
	 * @param zipcodeList list of given ranges
	 * @return List of minimal ZipcodeRange
	 */
	public List<ZipcodeRange> getMinimalZipcodeRangeList(List<ZipcodeRange> zipcodeList) {
		
		List<ZipcodeRange> orderedList = reorderZipcodeRangeList(zipcodeList);
		
		if (orderedList == null || orderedList.isEmpty() || orderedList.size() == 1) {
			return orderedList;
		}

		ZipcodeRange newZipcodeRange = null;
		
		Stack<ZipcodeRange> zipRangeStack = new Stack<>();
		zipRangeStack.push(orderedList.get(0));
		
		for (int i = 1; i < orderedList.size() ; i++) {
			ZipcodeRange top = zipRangeStack.peek();

			// ignoring if top and next range is same
			if(top.getZipcodeStart() == orderedList.get(i).getZipcodeStart() &&
			   top.getZipcodeEnd()	 == orderedList.get(i).getZipcodeEnd()) {
				continue;
			}
			// pushing if top end < next start
			else if(top.getZipcodeEnd() < orderedList.get(i).getZipcodeStart()){
				zipRangeStack.push(orderedList.get(i));
			}
			// create new range and push when top end is >= next start and top end is < next end 
			else if(top.getZipcodeEnd() >= orderedList.get(i).getZipcodeStart()) {
				if (top.getZipcodeEnd() < orderedList.get(i).getZipcodeEnd()) {
					newZipcodeRange = new ZipcodeRange(top.getZipcodeStart(), orderedList.get(i).getZipcodeEnd());
					zipRangeStack.remove(top);
					zipRangeStack.push(newZipcodeRange);
				}
			}
		}
		
		List<ZipcodeRange> finalList = new ArrayList<ZipcodeRange>(zipRangeStack);
		finalList.sort(Comparator.comparingInt(ZipcodeRange::getZipcodeStart));
		return finalList;

	}


	/**
	 * This method is touching each ZipcodeRange object in list and cleanup null and out of range value. 
	 * Swapping value if zipcodeEnd < zipcodeStart. at last sorting the filtered list.
	 * @param zipList List of given ranges.
	 * @return List of ordered ZipcodeRange. 
	 */
	public List<ZipcodeRange> reorderZipcodeRangeList(List<ZipcodeRange> zipList) {
		
		if (zipList == null || zipList.isEmpty() ) {
			return zipList;
		}
		
		Stack<ZipcodeRange> zipStack = new Stack<>();
		ZipcodeRange newZipcodeRange = null;

		for (int i = 0; i < zipList.size(); i++) {
			// ignoring null 
			if (zipList.get(i) == null ) {
				continue;
			}
			// ignoring out of range value
			else if(zipList.get(i).getZipcodeStart() <  MIN_ZIP_START || zipList.get(i).getZipcodeEnd() > MAX_ZIP_END ||
					zipList.get(i).getZipcodeEnd() <  MIN_ZIP_START || zipList.get(i).getZipcodeStart() > MAX_ZIP_END) {
				continue;
			}
			// Swapping value if zipcodeEnd < zipcodeStart
			else if ((zipList.get(i).getZipcodeEnd() < zipList.get(i).getZipcodeStart())) {
					newZipcodeRange = new ZipcodeRange(zipList.get(i).getZipcodeEnd(), zipList.get(i).getZipcodeStart());
					zipStack.push(newZipcodeRange);
					continue;
			} 
			else {
				zipStack.push(zipList.get(i));
			}
		}
		
		List<ZipcodeRange> filteredList = new ArrayList<ZipcodeRange>(zipStack);
		
		// Sorting the filtered list using zipcodeStart
		if (filteredList.size() > 1) {
			filteredList.sort(Comparator.comparingInt(ZipcodeRange::getZipcodeStart));
		}
		
		return filteredList;
	}


}
