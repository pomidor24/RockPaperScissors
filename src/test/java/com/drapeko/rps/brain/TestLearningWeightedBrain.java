package com.drapeko.rps.brain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.resolution.RPSDecisionMaker;

@RunWith(Parameterized.class)
public class TestLearningWeightedBrain {

	private static int ITERATIONS_COUNT = 25;

	private LearningWeightedBrain<RockPaperScissors> brain;
	private WeightedBrain<RockPaperScissors> player;
	private RPSDecisionMaker decisionMaker = new RPSDecisionMaker();


	@Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
    	Map<RockPaperScissors, Integer> playerMap = new HashMap<RockPaperScissors, Integer>();
    	playerMap.put(RockPaperScissors.PAPER, 1); 
    	playerMap.put(RockPaperScissors.ROCK, 1); 
    	playerMap.put(RockPaperScissors.SCISSORS, 1);    	
    	

    	Map<RockPaperScissors, Integer> m1 = new HashMap<RockPaperScissors, Integer>();
    	m1.put(RockPaperScissors.PAPER, 1); 
    	m1.put(RockPaperScissors.ROCK, 1); 
    	m1.put(RockPaperScissors.SCISSORS, 1);
    	
    	Map<RockPaperScissors, Integer> m2 = new HashMap<RockPaperScissors, Integer>();
    	m2.put(RockPaperScissors.PAPER, 15); 
    	m2.put(RockPaperScissors.ROCK, 5); 
    	m2.put(RockPaperScissors.SCISSORS, 5);
    	
    	Map<RockPaperScissors, Integer> m3 = new HashMap<RockPaperScissors, Integer>();
    	m3.put(RockPaperScissors.PAPER, 10); 
    	m3.put(RockPaperScissors.ROCK, 10); 
    	m3.put(RockPaperScissors.SCISSORS, 20);
    	
        return Arrays.asList(new Object[][] {{m1, playerMap}, {m2, playerMap}, {m3, playerMap}});
    }
    
	public TestLearningWeightedBrain(Map<RockPaperScissors, Integer> map, Map<RockPaperScissors, Integer> playerMap) {
		brain = new LearningWeightedBrain<RockPaperScissors>(map);
		player = new WeightedBrain<RockPaperScissors>(playerMap);
	}
	
	
	@Test
	public void testLearning() {
		
		for (int i = 0; i < ITERATIONS_COUNT; i++) {
			testOneIteration();
		}

	}
	
	private void testOneIteration() {
		int sumBefore = brain.getWeightsSum();
		
		RockPaperScissors randomDecision = player.thinkAndDecide();
		RockPaperScissors compDecision = brain.thinkAndDecide();
		
		int weightRandomBefore = brain.weightedMap.get(randomDecision);
		
		RockPaperScissors result = decisionMaker.makeDecision(randomDecision, compDecision);
		brain.handleResult(compDecision, randomDecision, result);
		
		int weightRandomAfter = brain.weightedMap.get(randomDecision);
		
		if (result == null || result.equals(compDecision)) {
			assertEquals(weightRandomBefore, weightRandomAfter);
		} else {
			int increaseBy = Math.max(1, (int) Math.ceil((double)weightRandomBefore / sumBefore));
			assertEquals(weightRandomBefore + increaseBy, weightRandomAfter);
		}
	}
}
