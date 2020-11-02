package com.wsi;

import java.util.LinkedList;
import java.util.List;

import com.wsi.entity.ZipcodeRange;
import com.wsi.service.ZipcodeService;

/** 
* <h1>Main class to calculate minimal zipcode ranges!</h1>  
* 
* @author  Ashish Porwal 
* @version 1.0 
* @since   11-01-2020 
*/
public class ZipcodeController {

    /** 
    * This is the main method which is using zipcodeOperations. 
    * @param args Unused. 
    * @return Nothing. 
    */
	public static void main(String[] args) {
		
		ZipcodeRange z1 = new ZipcodeRange(10200, 20000);
		ZipcodeRange z2 = new ZipcodeRange(15000, 10500);
		ZipcodeRange z3 = new ZipcodeRange(20001, 30000);
		ZipcodeRange z4 = new ZipcodeRange(10000, 20000);
		ZipcodeRange z5 = null;
		ZipcodeRange z6 = new ZipcodeRange(-60000, 40000);
		ZipcodeRange z7 = new ZipcodeRange(10300, 10800);
		
		List<ZipcodeRange> zipList1 = new LinkedList<>();
		zipList1.add(z5);
		zipList1.add(z1);
		zipList1.add(z2);
		zipList1.add(z5);
		zipList1.add(z3);
		zipList1.add(z4);
		zipList1.add(z5);
		zipList1.add(z6);
		zipList1.add(z7);

		zipList1.forEach(System.out::println);
		zipcodeOperations(zipList1);
	}

	
	
    /** 
    * This method is using getMinimalZipcodeRangeList in ZipcodeService to display minimal zipcode list.  
    * @param zipList input to getMinimalZipcodeRangeList. 
    * @return Nothing. 
    */
	private static void zipcodeOperations(List<ZipcodeRange> zipList) {
		
		ZipcodeService service = new ZipcodeService();
		List<ZipcodeRange> finalList = service.getMinimalZipcodeRangeList(zipList);
		System.out.println(" ********************  Minimal Zipcode List ******************** ");
		finalList.forEach(System.out::println);
	}
	
}
