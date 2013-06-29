package com.drapeko.rps.resolution;

import com.drapeko.rps.choice.Choice;

public abstract class DecisionMaker<T extends Choice> {

	/**
	 * Returns the winner choice or null if it's a draw
	 * @param choicePlayerOne
	 * @param choicePlayerTwo
	 * @return the winner or null if it's draw
	 */
	public abstract T makeDecision(T choicePlayerOne, T choicePlayerTwo);

	public abstract T getChoiceThatBeats(T choice);
}
