package com.drapeko.rps.manager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.drapeko.rps.BaseIntegrationTest;
import com.drapeko.rps.IntegrationTest;
import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Opponent;

@Category(IntegrationTest.class)
public class TestIntegrationRobotFactoryManagerImpl extends BaseIntegrationTest {

	@Autowired
	private RobotFactoryManager robotFactoryManager;
	
	@Test
	public void testGameManagerInit() {
		assertNotNull(robotFactoryManager);
	}
	
	@Test
	public void testWeightedRobotCreation() {
		Opponent<RockPaperScissors> robot = robotFactoryManager.createRandomRobot("random");
		assertNotNull(robot);
		
		RockPaperScissors result = robot.decide();
		assertNotNull(result);
		robot.viewResult(result, result, null);
	}
	
	@Test
	public void testRandomLearningRobotCreation() {
		Opponent<RockPaperScissors> robot = robotFactoryManager.createRandomLearningRobot("learning");
		assertNotNull(robot);
		
		RockPaperScissors result = robot.decide();
		assertNotNull(result);
		robot.viewResult(result, result, null);
	}
	
	@Test
	public void testShannonRobotCreation() {
		Opponent<RockPaperScissors> robot = robotFactoryManager.createShannonRobot("shannon");
		assertNotNull(robot);
		
		RockPaperScissors result = robot.decide();
		assertNotNull(result);
		robot.viewResult(result, result, null);
	}
	
	@Test
	public void testRandomShannonCreation() {
		Opponent<RockPaperScissors> robot = robotFactoryManager.createRandomShannonBrain("shandom");
		assertNotNull(robot);
		RockPaperScissors result = robot.decide();
		assertNotNull(result);
	}
}
