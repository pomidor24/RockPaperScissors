package com.drapeko.rps.brain;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.drapeko.rps.choice.RockPaperScissors;

@RunWith(Parameterized.class)
public class TestWeightedBrain {
	
	private static final int ITERATIONS = 5000;
	private static final double VARIANCE = 0.2;
	
	private WeightedBrain<RockPaperScissors> brain;
	private Map<RockPaperScissors, Integer> choicesAndWeightsMap;
	private Map<RockPaperScissors, Integer> distributionMap;
	private int weightsSum = 0;
    	
	@Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
    	
    	Map<RockPaperScissors, Integer> m1 = new HashMap<RockPaperScissors, Integer>();
    	m1.put(RockPaperScissors.PAPER, 1); 

    	Map<RockPaperScissors, Integer> m2 = new HashMap<RockPaperScissors, Integer>();
    	m2.put(RockPaperScissors.PAPER, 13);
    	m2.put(RockPaperScissors.ROCK, 2); 
    	
    	Map<RockPaperScissors, Integer> m3 = new HashMap<RockPaperScissors, Integer>();
    	m3.put(RockPaperScissors.PAPER, 10); 
    	m3.put(RockPaperScissors.ROCK, 10); 
    	m3.put(RockPaperScissors.SCISSORS, 20);
    	
        return Arrays.asList(new Object[][] {{m1}, {m2}, {m3}});
    }
    

    public TestWeightedBrain(Map<RockPaperScissors, Integer> map) {
    	brain = new WeightedBrain<RockPaperScissors>(map);
    	choicesAndWeightsMap = map;
    	distributionMap = new HashMap<RockPaperScissors, Integer>();
    	
    	for (Map.Entry<RockPaperScissors, Integer> parameter : choicesAndWeightsMap.entrySet()) {
    		weightsSum += parameter.getValue();
    	}
    }

    
	@Test
	public void testWeightedDistribution() {
		populateMapByRunningNtimesAndVerifyOutput();
		checkDistribution();
	}
	
	private void populateMapByRunningNtimesAndVerifyOutput() {
		for (int i = 0; i < ITERATIONS; i++) {
			RockPaperScissors choice = brain.thinkAndDecide();
			
			assertTrue(choicesAndWeightsMap.containsKey(choice));
			
			if (!distributionMap.containsKey(choice)) {
				distributionMap.put(choice, 0);
			}
			distributionMap.put(choice, distributionMap.get(choice)+1);
		}
	}
	
	private void checkDistribution() {
		for (Map.Entry<RockPaperScissors, Integer> record : distributionMap.entrySet()) {
			double currPercent = (double)record.getValue() / ITERATIONS;
			double expectedPercent = (double)choicesAndWeightsMap.get(record.getKey()) / weightsSum;
			double maxExpectedPercent = expectedPercent * (1+VARIANCE);
			double minExpectedPercent = expectedPercent * (1-VARIANCE);
			
			assertThat(currPercent, allOf(
				greaterThanOrEqualTo(minExpectedPercent),
				lessThanOrEqualTo(maxExpectedPercent)
			));
		}
	}
}
