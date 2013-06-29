package com.drapeko.rps.opponent;

public class Human<T> extends Opponent<T> {

	private T choice;
	
	public Human() {
		setName("human");
	}
	
	public Human(T choice) {
		this.choice = choice;
	}
	
	public void decide(T choice) {
		this.choice = choice;
	}
	
	@Override
	public String getName() {
		return "human";
	}
	
	@Override
	public T decide() {
		return choice;
	}

	@Override
	public void viewResult(T me, T opponent, T res) {
	}

}
