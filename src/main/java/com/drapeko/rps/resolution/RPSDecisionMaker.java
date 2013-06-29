package com.drapeko.rps.resolution;

import com.drapeko.rps.choice.RockPaperScissors;

/**
 * Rock Paper Scissors decision maker
 * @author romandrapeko
 * 
 */
public class RPSDecisionMaker extends DecisionMaker<RockPaperScissors> {

	@Override
	public RockPaperScissors makeDecision(RockPaperScissors playerOne, RockPaperScissors playerTwo) {
		if (playerOne == null || playerTwo == null) {
			throw new NullPointerException();
		}
		
		if (playerOne.equals(playerTwo)) {
			return null;
		}
		
		if (playerOne.equals(RockPaperScissors.ROCK) && 
				playerTwo.equals(RockPaperScissors.SCISSORS)) {
			return playerOne;
		}

		if (playerOne.equals(RockPaperScissors.PAPER) && 
				playerTwo.equals(RockPaperScissors.ROCK)) {
			return playerOne;
		}
		
		if (playerOne.equals(RockPaperScissors.SCISSORS) && 
				playerTwo.equals(RockPaperScissors.PAPER)) {
			return playerOne;
		}
		
		return playerTwo;
	}

	@Override
	public RockPaperScissors getChoiceThatBeats(RockPaperScissors choice) {
		RockPaperScissors res = null;
		
		if (choice == RockPaperScissors.PAPER) {
			res = RockPaperScissors.SCISSORS;
		} else if (choice == RockPaperScissors.SCISSORS) {
			res = RockPaperScissors.ROCK;
		} else if (choice == RockPaperScissors.ROCK) {
			res = RockPaperScissors.PAPER;
		}
		
		return res;
	}

}
