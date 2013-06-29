package com.drapeko.rps.manager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;
import com.drapeko.rps.opponent.Robot;
import com.drapeko.rps.resolution.DecisionMaker;

public class TestGameManager {

	GameManagerImpl gameManager;
	DecisionMaker<RockPaperScissors> decisionMaker;
	OpponentManager opponentManager;
	
	Robot<RockPaperScissors> opponent1;
	Robot<RockPaperScissors> opponent2;
	
	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		decisionMaker = (DecisionMaker<RockPaperScissors>) mock(DecisionMaker.class);
		opponentManager = mock(OpponentManager.class);
		gameManager = new GameManagerImpl();
		gameManager.setDecisionMaker(decisionMaker);
		gameManager.setOpponentManager(opponentManager);
		
		opponent1 = (Robot<RockPaperScissors>) mock(Robot.class);
		opponent2 = (Robot<RockPaperScissors>) mock(Robot.class);
		when(opponent1.getName()).thenReturn("robot1");
		when(opponent2.getName()).thenReturn("robot2");
	}
	
	@Test
	public void testGetKey() {

		String key = gameManager.getGameKey(opponent1, opponent2);
		verify(opponent1, times(1)).getName();
		verify(opponent2, times(1)).getName();
		
		assertEquals(opponent1.getName()+"_"+opponent2.getName(), key);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetScoresAndNullify() {
		
		Scores scores = gameManager.getScores(opponent1, opponent2);
		assertNotNull(scores);
		assertEquals(0, scores.getDraw());
		
		scores.incDraw();
		scores = gameManager.getScores(opponent1, opponent2);
		assertEquals(1, scores.getDraw());

		gameManager.nullifyGame(opponent1, opponent2);
		
		scores = gameManager.getScores(opponent1, opponent2);
		assertEquals(0, scores.getDraw());
		
		verify(opponentManager, times(2)).nullifyRobot(any(Robot.class));
	}

	
	@Test
	public void testNextStep() {
		
		when(opponent1.decide()).thenReturn(RockPaperScissors.PAPER);
		when(opponent2.decide()).thenReturn(RockPaperScissors.ROCK);
		when(decisionMaker.makeDecision(RockPaperScissors.PAPER, RockPaperScissors.ROCK)).thenReturn(RockPaperScissors.PAPER);
		
		RockPaperScissors result = gameManager.nextStep(opponent1, opponent2);
		assertEquals(RockPaperScissors.PAPER, result);
		
		verify(opponent1, times(1)).viewResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.PAPER);
		verify(opponent2, times(1)).viewResult(RockPaperScissors.ROCK, RockPaperScissors.PAPER, RockPaperScissors.PAPER);

		Scores scores = gameManager.getScores(opponent1, opponent2);
		assertEquals(1, scores.getFirstWon());
	}
	

}
