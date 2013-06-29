package com.drapeko.rps.opponent;

import com.drapeko.rps.brain.Brain;

public class StrategicRobot<T> extends Robot<T> {

	Brain<T> brain;
	
	public StrategicRobot(String name, Brain<T> brain) {
		this.brain = brain;
		setName(name);
	}
	
	@Override
	public T decide() {
		return brain.thinkAndDecide();
	}

	@Override
	public void viewResult(T me, T opponent, T res) {
		brain.handleResult(me, opponent, res);
	}

	@Override
	public T getLatestDecision() {
		return brain.getLatestDecision();
	}

}
