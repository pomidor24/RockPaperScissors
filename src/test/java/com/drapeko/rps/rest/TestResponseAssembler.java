package com.drapeko.rps.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;

public class TestResponseAssembler {

	private ResponseAssembler assembler = new ResponseAssembler();
	
	@Test
	public void testScoresResponse() {
		Scores scores = new Scores();
		scores.incDraw();
		scores.incDraw();
		scores.incFirstWon();
		
		ScoresResponse response = assembler.assemble(scores);
		assertEquals(scores.getDraw(), response.getDraw());
		assertEquals(scores.getFirstWon(), response.getFirstWon());
		assertEquals(scores.getSecondWon(), response.getSecondWon());
	}
	
	@Test
	public void testTrueResponse() {
		TrueResponse response = assembler.assemble(true);
		TrueResponse response2 = assembler.assemble(false);
		assertTrue(response.getYes());
		assertFalse(response2.getYes());
	}
	
	@Test
	public void testDecisionResponse() {
		Scores scores = new Scores();
		scores.incDraw();
		scores.incFirstWon();
		scores.incSecondWon();
		scores.incSecondWon();
		
		DecisionResponse response = assembler.assemble(RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.PAPER, scores);
		
		assertEquals(scores.getDraw(), response.getScores().getDraw());
		assertEquals(scores.getFirstWon(), response.getScores().getFirstWon());
		assertEquals(scores.getSecondWon(), response.getScores().getSecondWon());
		assertEquals("first", response.getWon());
		assertEquals(RockPaperScissors.PAPER.getName(), response.getFirst());
		assertEquals(RockPaperScissors.ROCK.getName(), response.getSecond());

		response = assembler.assemble(RockPaperScissors.PAPER, RockPaperScissors.PAPER, null, scores);
		assertEquals("draw", response.getWon());
	}
}
