package com.drapeko.rps.choice;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestRockPaperScissorsBadValue {

	@Test
	public void testBadValues() {
		assertNull(RockPaperScissors.fromId(100));
		assertNull(RockPaperScissors.fromId(3));
		assertNull(RockPaperScissors.fromName("badValue"));
		assertNull(RockPaperScissors.fromName("superBadValue"));
	}
}
