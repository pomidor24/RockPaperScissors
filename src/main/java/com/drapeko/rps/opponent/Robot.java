package com.drapeko.rps.opponent;

public abstract class Robot<T> extends Opponent<T> {
	
	public abstract T getLatestDecision();
}
