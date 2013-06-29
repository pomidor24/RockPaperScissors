package com.drapeko.rps.rest;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;

public class ResponseAssembler {

	public ScoresResponse assemble(Scores scores) {
		
		ScoresResponse response = new ScoresResponse();
		
		response.setFirstWon(scores.getFirstWon());
		response.setSecondWon(scores.getSecondWon());
		response.setDraw(scores.getDraw());

		return response;
	}
	
	public DecisionResponse assemble(RockPaperScissors first, RockPaperScissors second, RockPaperScissors result, Scores scores) {
		DecisionResponse response = new DecisionResponse();
		response.setScores(assemble(scores));
		
		String won = "draw";
		if (first.equals(result)) {
			won = "first";
		} else if (second.equals(result)) {
			won = "second";
		}
		
		response.setFirst(first.getName());
		response.setSecond(second.getName());
		response.setWon(won);
		
		return response;
	}
	
	public TrueResponse assemble(boolean yes) {
		TrueResponse response = new TrueResponse();
		response.setYes(yes);
		return response;
	}
}
