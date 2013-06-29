package com.drapeko.rps.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.drapeko.rps.BaseIntegrationTest;
import com.drapeko.rps.IntegrationTest;


@Category(IntegrationTest.class)
public class TestIntegrationRPSGameService extends BaseIntegrationTest {
	
	@Autowired
	private RPSGameService rpsGameService;


	@Test
    public void testCreatedScoresForNonExistentRobotsAndNullify() {
    	ScoresResponse response = rpsGameService.getScores("random_i_dont_exist", "shannon_i_dont_exist");
    	assertEquals(0, response.getDraw());
    	assertEquals(0, response.getFirstWon());
    	assertEquals(0, response.getSecondWon());
    	TrueResponse resp = rpsGameService.nullifyRobots("random_i_dont_exist", "shannon_i_dont_exist");
    	assertTrue(resp.getYes());
	}
    
	@Test
    public void testNullifyForNonExistent() {
    	TrueResponse response = rpsGameService.nullifyRobots("random_i_dont_exist2", "shannon_i_dont_exist2");
    	assertTrue(response.getYes());
	}

	// of course should be appropriate exception, not enough time :(
	@Test(expected= IllegalArgumentException.class) 
	public void testBadName() {
    	rpsGameService.getScores("badname1", "badname2");
	}
	
	@Test
	public void testNextStepRobots() {
    	DecisionResponse response = rpsGameService.robotDecide("shannon_1", "shandom_1");
    	ScoresResponse scores = response.getScores();
    	
    	assertEquals(1, scores.getDraw() + scores.getFirstWon() + scores.getSecondWon());
    	
    	response = rpsGameService.robotDecide("shannon_1", "shandom_1");
    	response = rpsGameService.robotDecide("shannon_1", "shandom_1");
    	response = rpsGameService.robotDecide("shannon_1", "shandom_1");
    	scores = response.getScores();
    	
    	assertEquals(4, scores.getDraw() + scores.getFirstWon() + scores.getSecondWon());

    	ScoresResponse scoresResp = rpsGameService.getScores("shannon_1", "shandom_1");
    	assertEquals(4, scoresResp.getDraw() + scoresResp.getFirstWon() + scoresResp.getSecondWon());
   	
    	TrueResponse resp = rpsGameService.nullifyRobots("shannon_1", "shandom_1");
    	assertTrue(resp.getYes());

    	scoresResp = rpsGameService.getScores("shannon_1", "shandom_1");
    	assertEquals(0, scoresResp.getDraw() + scoresResp.getFirstWon() + scoresResp.getSecondWon());
	}
	
	@Test
	public void testNextStepPerson() {
    	DecisionResponse response = rpsGameService.personDecide("learning_1", "Rock");
    	assertEquals("Rock", response.getFirst());
    	assertTrue(response.getSecond() != null);
    	
    	response = rpsGameService.personDecide("learning_1", "Paper");
    	assertEquals("Paper", response.getFirst());
    	
    	response = rpsGameService.personDecide("learning_1", "Scissors");
    	assertEquals("Scissors", response.getFirst());
    	
    	ScoresResponse scores = response.getScores();
    	assertEquals(3, scores.getDraw() + scores.getFirstWon() + scores.getSecondWon());
    	
    	scores = rpsGameService.getScores("human", "learning_1");
    	assertEquals(3, scores.getDraw() + scores.getFirstWon() + scores.getSecondWon());
   	
    	TrueResponse resp = rpsGameService.nullifyRobots("human", "learning_1");
    	assertTrue(resp.getYes());

    	scores = rpsGameService.getScores("human", "learning_1");
    	assertEquals(0, scores.getDraw() + scores.getFirstWon() + scores.getSecondWon());
	}
}
