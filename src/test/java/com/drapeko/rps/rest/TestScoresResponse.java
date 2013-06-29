package com.drapeko.rps.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestScoresResponse {
	@Test
	public void testScoresResponse() {
		ScoresResponse response = new ScoresResponse();
		response.setDraw(10);
		response.setFirstWon(3);
		response.setSecondWon(13);
		
		assertEquals(10, response.getDraw());
		assertEquals(3, response.getFirstWon());
		assertEquals(13, response.getSecondWon());
	}
}
