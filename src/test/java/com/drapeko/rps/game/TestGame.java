package com.drapeko.rps.game;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestGame {

	private Scores game = new Scores();
	
	@Test
	public void testGameInit() {
		assertEquals(0, game.getDraw());
		assertEquals(0, game.getFirstWon());
		assertEquals(0, game.getSecondWon());
	}
	
	@Test
	public void testGameIncrements() {
		game.incFirstWon();
		game.incFirstWon();
		assertEquals(2, game.getFirstWon());
		assertEquals(0, game.getSecondWon());
		assertEquals(0, game.getDraw());
		
		game.incSecondWon();
		game.incSecondWon();
		game.incSecondWon();
		assertEquals(2, game.getFirstWon());
		assertEquals(3, game.getSecondWon());
		assertEquals(0, game.getDraw());
		
		game.incDraw();
		assertEquals(2, game.getFirstWon());
		assertEquals(3, game.getSecondWon());
		assertEquals(1, game.getDraw());
		
		game.incSecondWon();
		game.incFirstWon();
		game.incDraw();
		assertEquals(3, game.getFirstWon());
		assertEquals(4, game.getSecondWon());
		assertEquals(2, game.getDraw());
	}
}
