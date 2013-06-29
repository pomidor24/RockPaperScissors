package com.drapeko.rps.choice;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestRockPaperScissors {
	
	private int id;
	private String name;
	
	@Parameters(name = "{index}: ({0})={1}{2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
    		{0, "Rock"}, {1, "Paper"}, {2, "Scissors"}
        });
    }
    
    public TestRockPaperScissors(int id, String name) {
    	this.id = id;
    	this.name = name;
    }

	@Test
	public void testGoodValue() {
		RockPaperScissors e1 = RockPaperScissors.fromId(id);
		RockPaperScissors e2 = RockPaperScissors.fromName(name);
		assertEquals(e1, e2);
		assertEquals(id, e1.getId());
		assertEquals(name, e1.getName());
	}
}
