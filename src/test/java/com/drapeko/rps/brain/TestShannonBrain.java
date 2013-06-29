package com.drapeko.rps.brain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.resolution.DecisionMaker;
import com.drapeko.rps.resolution.RPSDecisionMaker;


public class TestShannonBrain {
	
	private DecisionMaker<RockPaperScissors> decisionMaker = new RPSDecisionMaker();
	
	private RockPaperScissors [] choices = {
		RockPaperScissors.PAPER,
		RockPaperScissors.ROCK,
		RockPaperScissors.SCISSORS
	};

	@Test
	public void testShannonBrainInitAndMap() {
		
		ShannonBrain<RockPaperScissors> brain = new ShannonBrain<RockPaperScissors>(decisionMaker, 3, choices);
		int expectedRows = (int)Math.pow(choices.length+1, 6);
		assertEquals(expectedRows, brain.sequenceMap.size());
		
		int weight = brain.sequenceMap.get("1_0_N_N_2_N");
		
		assertEquals(0, weight);
	}
	
	@Test
	public void testAllMatchingSequences() {
		ShannonBrain<RockPaperScissors> brain = new ShannonBrain<RockPaperScissors>(decisionMaker, 4, choices);
	
		Set<String> keys = brain.getAllMatchingSequenceKeys();
		assertEquals(1, keys.size()); // for new key should be one
		assertTrue(keys.contains("N_N_N_N_N_N_N_N"));
		
		brain.sequence.add(RockPaperScissors.PAPER);
		keys = brain.getAllMatchingSequenceKeys();
		assertEquals(2, keys.size());
		assertTrue(keys.contains("N_N_N_N_N_N_N_1"));
		assertTrue(keys.contains("N_N_N_N_N_N_N_N"));

		
		brain.sequence.add(RockPaperScissors.PAPER);
		keys = brain.getAllMatchingSequenceKeys();
		assertEquals(4, keys.size()); 
		
		brain.sequence.add(RockPaperScissors.PAPER);
		brain.sequence.add(RockPaperScissors.PAPER);
		brain.sequence.add(RockPaperScissors.PAPER);
		brain.sequence.add(RockPaperScissors.PAPER);
		brain.sequence.add(RockPaperScissors.PAPER);
		brain.sequence.add(RockPaperScissors.PAPER);
		
		keys = brain.getAllMatchingSequenceKeys();
		
		assertEquals(256, keys.size()); 
		assertTrue(keys.contains("1_N_1_N_N_N_N_1"));
		assertTrue(keys.contains("1_N_1_1_1_N_N_1"));
		assertTrue(keys.contains("1_1_1_1_1_1_1_1"));
	}
	
	@Test
	public void testAllMatchingCandidates() {
		ShannonBrain<RockPaperScissors> brain = new ShannonBrain<RockPaperScissors>(decisionMaker, 3, choices);
		Set<String> keys = brain.getAllMatchingCandidates();
		assertEquals(3, keys.size()); 
		assertTrue(keys.contains("N_N_N_N_N_0"));
		assertTrue(keys.contains("N_N_N_N_N_1"));
		assertTrue(keys.contains("N_N_N_N_N_2"));
		
		brain.previousChoice = RockPaperScissors.ROCK;
		brain.sequence.add(RockPaperScissors.PAPER);
		keys = brain.getAllMatchingCandidates();
		assertEquals(6, keys.size()); 
	}
	
	@Test
	// TODO
	public void testGetWeightsSum() {
		
	}
	
	
	@Test
	public void testThinkAndDecide() {
		
		ShannonBrain<RockPaperScissors> brain = new ShannonBrain<RockPaperScissors>(decisionMaker, 3, choices);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.PAPER, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.PAPER, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.PAPER, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.PAPER, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, null);
		brain.handleResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, null);
		
		// should identify the sequence and kill paper
		assertEquals(RockPaperScissors.SCISSORS, brain.thinkAndDecide()); // to kill paper

	}
	
}
