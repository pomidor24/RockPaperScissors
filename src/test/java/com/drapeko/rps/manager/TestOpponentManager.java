package com.drapeko.rps.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Robot;

public class TestOpponentManager {

	private OpponentManagerImpl manager;
	private RobotFactoryManager factoryManager;
	
	@Before
	public void init() {
		manager = new OpponentManagerImpl();
		factoryManager = mock(RobotFactoryManager.class);
		manager.setRobotFactoryManager(factoryManager);
	}
	
	@Test(expected= IllegalArgumentException.class) 
	public void testGetRobotBadName() {
		manager.getRobot("bad bad bad");
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void testGetRandomRobot() {
		String name = "random_guy";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(factoryManager.createRandomRobot(name)).thenReturn(robot);
		
		Robot<RockPaperScissors> result1 = manager.getRobot(name);
		Robot<RockPaperScissors> result2 = manager.getRobot(name);
		verify(factoryManager, times(1)).createRandomRobot(name);
		
		assertEquals(result1, result2);
		assertEquals(result1, robot);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLearning() {
		String name = "learning_robot";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(factoryManager.createRandomLearningRobot(name)).thenReturn(robot);
		
		Robot<RockPaperScissors> result1 = manager.getRobot(name);
		Robot<RockPaperScissors> result2 = manager.getRobot(name);
		verify(factoryManager, times(1)).createRandomLearningRobot(name);
		
		assertEquals(result1, result2);
		assertEquals(result1, robot);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetShannonRobot() {
		String name = "shannon_robot";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(factoryManager.createShannonRobot(name)).thenReturn(robot);
		
		Robot<RockPaperScissors> result1 = manager.getRobot(name);
		Robot<RockPaperScissors> result2 = manager.getRobot(name);
		verify(factoryManager, times(1)).createShannonRobot(name);
		
		assertEquals(result1, result2);
		assertEquals(result1, robot);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetShandomRobot() {
		String name = "shandom_robot";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(factoryManager.createRandomShannonBrain(name)).thenReturn(robot);
		
		Robot<RockPaperScissors> result1 = manager.getRobot(name);
		Robot<RockPaperScissors> result2 = manager.getRobot(name);
		verify(factoryManager, times(1)).createRandomShannonBrain(name);
		
		assertEquals(result1, result2);
		assertEquals(result1, robot);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void nullifyRobot() {
		assertEquals(0, manager.robotsByName.size());
		
		String name = "shandom_robot";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(robot.getName()).thenReturn(name);
		when(factoryManager.createRandomShannonBrain(name)).thenReturn(robot);
		
		manager.getRobot(name);
		assertEquals(1, manager.robotsByName.size());

		manager.nullifyRobot(robot);
		assertEquals(0, manager.robotsByName.size());
		
	}
	
	@Test
	public void testHuman() {
		assertEquals("human", manager.getHuman().getName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHumanOrRobot() {
		String name = "shandom_robot";
		Robot<RockPaperScissors> robot = (Robot<RockPaperScissors>)mock(Robot.class);
		
		when(robot.getName()).thenReturn(name);
		when(factoryManager.createRandomShannonBrain(name)).thenReturn(robot);
		
		assertEquals("human", manager.getHumanOrRobot("human").getName());
		assertEquals(name, manager.getHumanOrRobot(name).getName());
	}
}
