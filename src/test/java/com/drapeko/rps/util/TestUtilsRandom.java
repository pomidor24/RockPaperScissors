package com.drapeko.rps.util;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilsRandom {
	
	private static int ITERATIONS_COUNT = 10;
	
	private int min;
	private int max;
	
	@Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
    		{0, 0}, {4, 14}, {0, 2}, {24, 555}, {1, 223} 
        });
    }
    
    public TestUtilsRandom(int min, int max) {
    	this.min = min;
    	this.max = max;
    }

	@Test
	public void testMinMaxRange() {
		for (int i = 0; i < ITERATIONS_COUNT; i++) {
			assertThat(Utils.getRandom(min, max), allOf(
				greaterThanOrEqualTo(min),
				lessThanOrEqualTo(max))
			);
		}
	}
}
