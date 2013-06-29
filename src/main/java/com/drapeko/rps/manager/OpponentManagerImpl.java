package com.drapeko.rps.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Human;
import com.drapeko.rps.opponent.Opponent;
import com.drapeko.rps.opponent.Robot;

@Component
public class OpponentManagerImpl implements OpponentManager {

	@Autowired
	RobotFactoryManager robotFactoryManager;
	
	Map<String, Robot<RockPaperScissors>> robotsByName = new HashMap<String, Robot<RockPaperScissors>>();

	
	public void setRobotFactoryManager(RobotFactoryManager robotFactoryManager) {
		this.robotFactoryManager = robotFactoryManager;
	}
	
	public Robot<RockPaperScissors> getRobot(String name) {
		if (!robotsByName.containsKey(name)) {
			Robot<RockPaperScissors> robot;
			
			if (name.contains("random")) {
				robot = robotFactoryManager.createRandomRobot(name);
			} else if (name.contains("learning")) {
				robot = robotFactoryManager.createRandomLearningRobot(name);
			} else if (name.contains("shannon")) {
				robot = robotFactoryManager.createShannonRobot(name);
			} else if (name.contains("shandom")) {
				robot = robotFactoryManager.createRandomShannonBrain(name);
			} else {
				throw new IllegalArgumentException("Name should contain random, learning or shannon");
			}
			
			robotsByName.put(name, robot);
		}
		
		return robotsByName.get(name);
	}

	public void nullifyRobot(Robot<RockPaperScissors> robot) {
		robotsByName.remove(robot.getName());
	}

	public Human<RockPaperScissors> getHuman() {
		return new Human<RockPaperScissors>();
	}

	public Opponent<RockPaperScissors> getHumanOrRobot(String name) {
		if (name.equals("human")) {
			return getHuman();
		}
		return getRobot(name);
	}

}
