package com.drapeko.rps.resolution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.drapeko.rps.choice.RockPaperScissors;

@RunWith(Parameterized.class)
public class TestRPSDecisionMaker {

	private RockPaperScissors one;
	private RockPaperScissors two;
	private RockPaperScissors expectedResult;
	private RPSDecisionMaker decisionMaker = new RPSDecisionMaker();
	
	@Parameters(name = "{index}: ({0})= {1} vs {2} expected {3}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
    		{RockPaperScissors.PAPER, 		RockPaperScissors.PAPER, 	null},
    		{RockPaperScissors.PAPER, 		RockPaperScissors.ROCK, 	RockPaperScissors.PAPER},
    		{RockPaperScissors.PAPER, 		RockPaperScissors.SCISSORS, RockPaperScissors.SCISSORS},
    		{RockPaperScissors.ROCK, 		RockPaperScissors.ROCK, 	null},
    		{RockPaperScissors.ROCK, 		RockPaperScissors.PAPER, 	RockPaperScissors.PAPER},
    		{RockPaperScissors.ROCK, 		RockPaperScissors.SCISSORS, RockPaperScissors.ROCK},
    		{RockPaperScissors.SCISSORS, 	RockPaperScissors.SCISSORS, null},
    		{RockPaperScissors.SCISSORS, 	RockPaperScissors.PAPER, 	RockPaperScissors.SCISSORS},
    		{RockPaperScissors.SCISSORS, 	RockPaperScissors.ROCK, 	RockPaperScissors.ROCK},
        });
    }
    
    public TestRPSDecisionMaker(RockPaperScissors one, RockPaperScissors two, RockPaperScissors expectedResult) {
    	this.one = one;
    	this.two = two;
    	this.expectedResult = expectedResult;
    }
    
    @Test
    public void testDecisionMade() {
    	RockPaperScissors result = decisionMaker.makeDecision(one, two);
    	assertEquals(expectedResult, result);
    }
    
}
