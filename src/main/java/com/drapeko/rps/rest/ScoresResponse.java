package com.drapeko.rps.rest;

public class ScoresResponse {
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
	
	public void setFirstWon(int firstWon) {
		this.firstWon = firstWon;
	}
	
	public void setSecondWon(int secondWon) {
		this.secondWon = secondWon;
	}
	
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
