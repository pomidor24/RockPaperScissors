package com.drapeko.rps.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDecisionResponse {
	
	@Test
	public void testDecisionResponse() {
		DecisionResponse response = new DecisionResponse();
		
		ScoresResponse scores = new ScoresResponse();
		
		scores.setDraw(10);
		scores.setFirstWon(3);
		scores.setSecondWon(13);
		response.setScores(scores);
		response.setFirst("Rock");
		response.setSecond("Paper");
		response.setWon("first");
		
		assertEquals(scores, response.getScores());
		assertEquals("Rock", response.getFirst());
		assertEquals("Paper", response.getSecond());
		assertEquals("first", response.getWon());
	}
	
}
