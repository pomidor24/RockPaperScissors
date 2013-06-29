package com.drapeko.rps.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;
import com.drapeko.rps.opponent.Opponent;
import com.drapeko.rps.opponent.Robot;
import com.drapeko.rps.resolution.DecisionMaker;

@Component
public class GameManagerImpl implements GameManager {
	
	private Map<String, Scores> scoresMap = new HashMap<String, Scores>();
	
	@Autowired
	private DecisionMaker<RockPaperScissors> decisionMaker;
	
	@Autowired
	private OpponentManager opponentManager;
	
	public void setDecisionMaker(DecisionMaker<RockPaperScissors> decisionMaker) {
		this.decisionMaker = decisionMaker;
	}
	
	public void setOpponentManager(OpponentManager opponentManager) {
		this.opponentManager = opponentManager;
	}

	protected String getGameKey(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2) {
		String key = opponent1.getName() + "_" + opponent2.getName();
		return key;
	}
	
	@Override
	public RockPaperScissors nextStep(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2) {
		Scores scores = getScores(opponent1, opponent2);
		
		RockPaperScissors firstDecision = opponent1.decide();
		RockPaperScissors secondDecision = opponent2.decide();
		
		RockPaperScissors result = decisionMaker.makeDecision(firstDecision, secondDecision);
	
		if (result == null) {
			scores.incDraw();
		} else if (result.equals(firstDecision)) {
			scores.incFirstWon();
		} else if (result.equals(secondDecision)) {
			scores.incSecondWon();
		}
		
		opponent1.viewResult(firstDecision, secondDecision, result);
		opponent2.viewResult(secondDecision, firstDecision, result);
		
		return result;
	}
	
	@Override
	public Scores getScores(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2) {
		String key = getGameKey(opponent1, opponent2);
		
		if (!scoresMap.containsKey(key)) {
			scoresMap.put(key, new Scores());
		}
		
		return scoresMap.get(key);
	}

	public void nullifyGame(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2) {
		String key = getGameKey(opponent1, opponent2);
		scoresMap.put(key, new Scores());
		
		if (opponent1 instanceof Robot) {
			opponentManager.nullifyRobot((Robot<RockPaperScissors>) opponent1);
		}
		
		if (opponent2 instanceof Robot) {
			opponentManager.nullifyRobot((Robot<RockPaperScissors>) opponent2);
		}
	}
}
