package com.wsi.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.wsi.entity.ZipcodeRange;

public class ZipcodeServiceTest {
	
	ZipcodeService service = new ZipcodeService();
	
	@Test
	public void testGetMinimalZipcodeRangeList_NullRanges() {
		List<ZipcodeRange> inputList = null;
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		List<ZipcodeRange> expectedList = null;  
		assertEquals(expectedList, actualsList);
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_BorderRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(9999, 20000));
		inputList.add(new ZipcodeRange(12345, 100000));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		assertEquals(expectedList, actualsList);
	}
	
	
	@Test
	public void testGetMinimalZipcodeRangeList_EmptyRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		
		assertEquals(expectedList, actualsList);
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_SingleRange() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(10000, 20000));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(10000, 20000));  
		
		assertEquals(expectedList, actualsList);
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_ZeroDataRange() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(0, 0));
		inputList.add(new ZipcodeRange(10000, 20000));
		inputList.add(new ZipcodeRange(1000, 0));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(10000, 20000));  
		
		assertEquals(expectedList, actualsList);
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_UniqueRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(94133, 94133));
		inputList.add(new ZipcodeRange(94200, 94299));
		inputList.add(new ZipcodeRange(94600, 94699));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(94133, 94133));
		expectedList.add(new ZipcodeRange(94200, 94299));
		expectedList.add(new ZipcodeRange(94600, 94699));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	
	@Test
	public void testGetMinimalZipcodeRangeList_OverlappingRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(94133, 94133));
		inputList.add(new ZipcodeRange(94133, 94299));
		inputList.add(new ZipcodeRange(94600, 94699));
		inputList.add(new ZipcodeRange(94690, 94750));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(94133, 94299));
		expectedList.add(new ZipcodeRange(94600, 94750));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_AllOverlappingRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(94133, 94133));
		inputList.add(new ZipcodeRange(94133, 94299));
		inputList.add(new ZipcodeRange(94299, 94690));
		inputList.add(new ZipcodeRange(94690, 94750));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(94133, 94750));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	
	@Test
	public void testGetMinimalZipcodeRangeList_BigAndSmallRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(10000, 50000));
		inputList.add(new ZipcodeRange(20000, 30000));
		inputList.add(new ZipcodeRange(35000, 60000));
		inputList.add(new ZipcodeRange(70000, 70500));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(10000, 60000));
		expectedList.add(new ZipcodeRange(70000, 70500));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_SameRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(10000, 20000));
		inputList.add(new ZipcodeRange(10000, 20000));
		inputList.add(new ZipcodeRange(35000, 60000));
		inputList.add(new ZipcodeRange(60000, 70500));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(10000, 20000));
		expectedList.add(new ZipcodeRange(35000, 70500));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	@Test
	public void testGetMinimalZipcodeRangeList_BadDataRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(-10000, 20000));
		inputList.add(new ZipcodeRange(35000, 60000));
		inputList.add(new ZipcodeRange(60000, 70500));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(35000, 70500));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}
	
	
	@Test
	public void testGetMinimalZipcodeRangeList_UnorderedRanges() {
		List<ZipcodeRange> inputList = new LinkedList<>();  
		inputList.add(new ZipcodeRange(10000, 20000));
		inputList.add(new ZipcodeRange(20000, 10000));
		inputList.add(new ZipcodeRange(60000, 70500));
		inputList.add(new ZipcodeRange(35000, 60000));
		List<ZipcodeRange> actualsList = service.getMinimalZipcodeRangeList(inputList); 
		
		List<ZipcodeRange> expectedList = new LinkedList<>();  
		expectedList.add(new ZipcodeRange(10000, 20000));
		expectedList.add(new ZipcodeRange(35000, 70500));
		
		assertArrayEquals(expectedList.toArray(), actualsList.toArray());
	}

	
}
