package com.drapeko.rps.opponent;

import static org.junit.Assert.*;

import org.junit.Test;

import com.drapeko.rps.choice.RockPaperScissors;

public class TestHuman {

	@Test
	public void testHuman() {
		Human<RockPaperScissors> human = new Human<RockPaperScissors>();
		human.decide(RockPaperScissors.SCISSORS);
		assertEquals("human", human.getName());
		assertEquals(RockPaperScissors.SCISSORS, human.decide());
	}
}
