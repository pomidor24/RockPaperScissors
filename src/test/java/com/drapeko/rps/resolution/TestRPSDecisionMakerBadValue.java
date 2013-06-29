package com.drapeko.rps.resolution;

import org.junit.Test;

import com.drapeko.rps.choice.RockPaperScissors;

public class TestRPSDecisionMakerBadValue {

	private RPSDecisionMaker decisionMaker = new RPSDecisionMaker();
	
	@Test(expected= NullPointerException.class) 
	public void testFirstNull() {
		decisionMaker.makeDecision(null, RockPaperScissors.PAPER);
	}
	
	@Test(expected= NullPointerException.class) 
	public void testSecondNull() {
		decisionMaker.makeDecision(RockPaperScissors.ROCK, null);
	}
	
	@Test(expected= NullPointerException.class) 
	public void testBothNull() {
		decisionMaker.makeDecision(null, null);
	}
}
