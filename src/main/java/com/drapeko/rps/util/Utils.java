package com.drapeko.rps.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Utils {
	
	public static int getRandom(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public static <T> boolean areListElementsUnique(List<T> list) {
		Set<T> elements = new HashSet<T>();
		
		for (T e : list) {
			if (elements.contains(e)) {
				return false;
			}
			elements.add(e);
		}
		
		return true;
	}
	
	/**
	 * Returns a set of list with replacement of every 0 elements, every 1, every 2, ..., every N
	 * Example: (1,2,3) => (1,2,3) (R,2,3) (R,R,3) (R,R,R) (1,R,3) (1,R,R) (1,2,R)
	 * should be 2^n
	 */
	public static <T> Set<List<T>> getAllPermutatedReplacedLists(List<T> list, T replacement) {
		Set<List<T>> result = new HashSet<List<T>>();
		
		long size = 2 << list.size();
		for (long i = 0; i < size; i++) {
			List<T> elm = new ArrayList<T>();
			for (int j = 0; j < list.size(); j++) {
				if ((i >> j) % 2 == 1) {
					elm.add(list.get(j));
				} else {
					elm.add(null);
				}
			}
			result.add(elm);
		}
		return result;
	}
	
	/**
	 * Creates a list of all combinations of N size. For example,
	 * 1, 2, 3 with N = 3 ==> (1,1,1) (1,1,2) (1,1,3) (1,2,1) (1,2,2) (1,2,3) ... etc
	 * 
	 * @param number
	 * @param elms
	 * @return
	 */
	public static <T> List<List<T>> getAllCombinations(int number, List<T> elms) {
		List<List<T>> firstIterationList = new ArrayList<List<T>>();
		for (T elm : elms) {
			List<T> list = new ArrayList<T>();
			list.add(elm);
			firstIterationList.add(list);
		}
				
		return _getAllCombinations(number-1, firstIterationList, elms);
	}
	
	private static <T> List<List<T>> _getAllCombinations(int number, List<List<T>> currPairs, List<T> elms) {
		if (number == 0) {
			return currPairs;
		}
		
		List<List<T>> newResult = new ArrayList<List<T>>();
		
		for (List<T> pairs : currPairs) {			
			for (T elm : elms) {
				List<T> res = new ArrayList<T>();
				res.addAll(pairs);
				res.add(elm);
				newResult.add(res);
			}
		}
		
		return _getAllCombinations(number-1, newResult, elms);
	}

}
