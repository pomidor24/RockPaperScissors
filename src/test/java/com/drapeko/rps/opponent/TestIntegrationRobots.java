package com.drapeko.rps.opponent;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.drapeko.rps.BaseIntegrationTest;
import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.manager.RobotFactoryManager;
import com.drapeko.rps.opponent.Robot;
import com.drapeko.rps.resolution.DecisionMaker;

public class TestIntegrationRobots extends BaseIntegrationTest {
	
	@Autowired
	private RobotFactoryManager robotFactoryManager;
	
	@Autowired
	private DecisionMaker<RockPaperScissors> decisionMaker;
	

	@Test
	public void testRobots1() {	
		Robot<RockPaperScissors> random = robotFactoryManager.createRandomRobot("random");
		Robot<RockPaperScissors> learning = robotFactoryManager.createRandomLearningRobot("learning");
		testRobot(random, learning);
	}
	
	@Test
	public void testRobots2() {	
		Robot<RockPaperScissors> random = robotFactoryManager.createRandomRobot("shannon");
		Robot<RockPaperScissors> learning = robotFactoryManager.createRandomLearningRobot("shandom");
		testRobot(random, learning);
	}
	
	private void testRobot(Robot<RockPaperScissors> robot1, Robot<RockPaperScissors> robot2) {
		Set<RockPaperScissors> choices = new HashSet<RockPaperScissors>();
		choices.add(RockPaperScissors.PAPER);
		choices.add(RockPaperScissors.ROCK);
		choices.add(RockPaperScissors.SCISSORS);
		
		for (int i = 0; i < 1000; i++) {
			RockPaperScissors choice1 = robot1.decide();
			RockPaperScissors choice2 = robot2.decide();
			assertTrue(choices.contains(choice1));
			assertTrue(choices.contains(choice2));
			RockPaperScissors result = decisionMaker.makeDecision(choice1, choice2);
			robot1.viewResult(choice1, choice2, result);
			robot2.viewResult(choice2, choice1, result);
		}
	}
}
