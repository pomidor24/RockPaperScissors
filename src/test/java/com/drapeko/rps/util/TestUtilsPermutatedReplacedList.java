package com.drapeko.rps.util;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.hasItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilsPermutatedReplacedList {
	
	private Integer replacement;
	private List<Integer> list;

	@Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
    		{null, Arrays.asList(new Integer[]{1, 2})}, 
        	{null, Arrays.asList(new Integer[]{1, 2, 3, 4, 5})},
        	{null, Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})}
        });
    }
    
    public TestUtilsPermutatedReplacedList(Integer replacement, List<Integer> list) {
    	this.replacement = replacement;
    	this.list = list;
    }
    

    
    @Test
    public void testPermutations() {
    	Set<List<Integer>> res = Utils.getAllPermutatedReplacedLists(list, replacement);
    	assertEquals(2<<(list.size()-1), res.size());
    	
    	assertThat(res, hasItem(list));
    	
    	List<Integer> list2 = new ArrayList<Integer>(list);
    	list2.set(0, null);
    	assertThat(res, hasItem(list2));
    }
}
