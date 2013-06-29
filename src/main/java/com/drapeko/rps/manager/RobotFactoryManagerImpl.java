package com.drapeko.rps.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.drapeko.rps.brain.Brain;
import com.drapeko.rps.brain.LearningWeightedBrain;
import com.drapeko.rps.brain.RandomShannonBrain;
import com.drapeko.rps.brain.ShannonBrain;
import com.drapeko.rps.brain.WeightedBrain;
import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Robot;
import com.drapeko.rps.opponent.StrategicRobot;
import com.drapeko.rps.resolution.DecisionMaker;

@Component
public class RobotFactoryManagerImpl implements RobotFactoryManager {
	
	private Map<RockPaperScissors, Integer> randomWeightedMap;
	private Map<RockPaperScissors, Integer> learningWeightedMap;

	@Autowired
	private DecisionMaker<RockPaperScissors> decisionMaker;

	@Autowired
	@Qualifier("shannonMovesLength")
	private Integer shannonMovesLength;
	
	public RobotFactoryManagerImpl() {
		Map<RockPaperScissors, Integer> map = new HashMap<RockPaperScissors, Integer>();
    	map.put(RockPaperScissors.PAPER, 10); 
    	map.put(RockPaperScissors.ROCK, 10); 
    	map.put(RockPaperScissors.SCISSORS, 10);
    	randomWeightedMap = map;
    	learningWeightedMap = new HashMap<RockPaperScissors, Integer>(map);
	}

	public Robot<RockPaperScissors> createRandomRobot(String name) {
    	Map<RockPaperScissors, Integer> map = new HashMap<RockPaperScissors, Integer>(randomWeightedMap);
    	Brain<RockPaperScissors> brain = new WeightedBrain<RockPaperScissors>(map);
		
		return new StrategicRobot<RockPaperScissors>(name, brain);
	}

	public Robot<RockPaperScissors> createRandomLearningRobot(String name) {
		Map<RockPaperScissors, Integer> map = new HashMap<RockPaperScissors, Integer>(learningWeightedMap);
    	Brain<RockPaperScissors> brain = new LearningWeightedBrain<RockPaperScissors>(map);

		return new StrategicRobot<RockPaperScissors>(name, brain);
	}

	public Robot<RockPaperScissors> createShannonRobot(String name) {
		
		Brain<RockPaperScissors> brain = new ShannonBrain<RockPaperScissors>(
				decisionMaker, 
				shannonMovesLength, 
				new RockPaperScissors[] {
					RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.SCISSORS
				}
		);
		
		return new StrategicRobot<RockPaperScissors>(name, brain);
	}

	public Robot<RockPaperScissors> createRandomShannonBrain(String name) {
		Brain<RockPaperScissors> brain = new RandomShannonBrain<RockPaperScissors>(
				decisionMaker, 
				shannonMovesLength, 
				new RockPaperScissors[] {
					RockPaperScissors.PAPER, RockPaperScissors.ROCK, RockPaperScissors.SCISSORS
				}
		);
		
		return new StrategicRobot<RockPaperScissors>(name, brain);
	}

}
