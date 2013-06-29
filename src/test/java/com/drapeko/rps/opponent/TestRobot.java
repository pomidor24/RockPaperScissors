package com.drapeko.rps.opponent;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.drapeko.rps.brain.Brain;
import com.drapeko.rps.brain.ShannonBrain;
import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Robot;
import com.drapeko.rps.opponent.StrategicRobot;

public class TestRobot {
	
	Brain<RockPaperScissors> brain;
	Robot<RockPaperScissors> robot;
	
	@Before
	@SuppressWarnings("unchecked")
	public void init() {
		brain = (Brain<RockPaperScissors>)mock(ShannonBrain.class);
		robot = new StrategicRobot<RockPaperScissors>("test", brain);
	}
	
	
	@Test
	public void testDecideBehavior() {
		robot.decide();
		verify(brain, times(1)).thinkAndDecide();
	}
	
	@Test
	public void testViewResultsBehavior() {
		robot.viewResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.PAPER);
		verify(brain, times(1)).handleResult(RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.PAPER);
	}
	
	@Test
	public void testGetLatestResult() {
		robot.getLatestDecision();
		verify(brain, times(1)).getLatestDecision();		
	}
}
