package com.drapeko.rps.opponent;

public abstract class Opponent<T> {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public abstract T decide();
	
	public abstract void viewResult(T me, T opponent, T res);
	
}
