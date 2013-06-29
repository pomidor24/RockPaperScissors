package com.drapeko.rps.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;



public class TestUtils {
	
	@Test
	public void testListValuesAreUnique() {
		assertTrue(Utils.areListElementsUnique(Arrays.asList(new Integer[] {})));
		assertTrue(Utils.areListElementsUnique(Arrays.asList(new Integer[] {1})));
		assertTrue(Utils.areListElementsUnique(Arrays.asList(new Integer[] {1, 2, 3, 4, 5})));
		assertTrue(Utils.areListElementsUnique(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 100, 200, 300})));
	}
	
	@Test
	public void testListValuesAreNotUnique() {
		assertFalse(Utils.areListElementsUnique(Arrays.asList(new Integer[] {1, 1})));
		assertFalse(Utils.areListElementsUnique(Arrays.asList(new Integer[] {1, 1, 2, 3, 4, 5})));
		assertFalse(Utils.areListElementsUnique(Arrays.asList(new Integer[] {2, 3, 4, 5, 2})));
	}
	

	
	
	@Test
	public void testGetAllCombinations() {
		int numberOfElms = 4;
		
		List<Integer> elms = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, null});
		List<List<Integer>> res = Utils.getAllCombinations(numberOfElms, elms);
		int sum = (int)Math.pow(elms.size(), numberOfElms);
		assertEquals(sum, res.size());
		
		List<Integer> shouldExist = Arrays.asList(new Integer[] {1, 2, 2, 4});
		
		boolean isInList = false;
		for (List<Integer> list : res) {
			assertEquals(numberOfElms, list.size());
			isInList = isInList || list.containsAll(shouldExist);
		}
		
		assertTrue(isInList);
	}

}
