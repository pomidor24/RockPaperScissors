package com.drapeko.rps.rest;

public class DecisionResponse {

	private ScoresResponse scores;
	private String won;
	private String first;
	private String second;
	
	public void setScores(ScoresResponse scores) {
		this.scores = scores;
	}
	
	public ScoresResponse getScores() {
		return this.scores;
	}
	
	public void setWon(String won) {
		this.won = won;
	}
	
	public String getWon() {
		return this.won;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getFirst() {
		return this.first;
	}
	
	public void setSecond(String second) {
		this.second = second;
	}
	
	public String getSecond() {
		return this.second;
	}
}
