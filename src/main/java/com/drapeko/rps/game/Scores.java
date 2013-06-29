package com.drapeko.rps.game;

public class Scores {

	private int firstWon = 0;
	private int secondWon = 0;
	private int draw = 0;
	
	public int getFirstWon() {
		return firstWon;
	}
	
	public int getSecondWon() {
		return secondWon;
	}
	
	public int getDraw() {
		return draw;
	}
	
	public void incFirstWon() {
		firstWon++;
	}
	
	public void incSecondWon() {
		secondWon++;
	}
	
	public void incDraw() {
		draw++;
	}
}
