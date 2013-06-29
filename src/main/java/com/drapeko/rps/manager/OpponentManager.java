package com.drapeko.rps.manager;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.opponent.Human;
import com.drapeko.rps.opponent.Opponent;
import com.drapeko.rps.opponent.Robot;

public interface OpponentManager {

	public Robot<RockPaperScissors> getRobot(String id);
	
	public Human<RockPaperScissors> getHuman();
	
	public void nullifyRobot(Robot<RockPaperScissors> opponent1);
	
	public Opponent<RockPaperScissors> getHumanOrRobot(String name);
}
