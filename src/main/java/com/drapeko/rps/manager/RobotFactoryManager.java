package com.drapeko.rps.manager;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Robot;

public interface RobotFactoryManager {

	Robot<RockPaperScissors> createRandomRobot(String name);
	
	Robot<RockPaperScissors> createRandomLearningRobot(String name);
	
	Robot<RockPaperScissors> createShannonRobot(String name);
	
	Robot<RockPaperScissors> createRandomShannonBrain(String name);
}
