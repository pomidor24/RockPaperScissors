package com.drapeko.rps.brain;

/**
 * Brain is class used by Robots to make decision for the next step
 * @author drapeko
 */
public abstract class Brain<T> {
	
	/**
	 * Previous choice made
	 */
	protected T previousChoice;
	
	protected T latestChoice;
	
	/**
	 * Make a decision and return the value
	 * @return decision value
	 */
	public abstract T thinkAndDecide();
	
	/**
	 * Learn by viewing the result
	 */
	public abstract void handleResult(T me, T opponent, T res);
	
	public T getLatestDecision() {
		return latestChoice;
	}
	
	public T getPreviousDecision() {
		return previousChoice;
	}
}
