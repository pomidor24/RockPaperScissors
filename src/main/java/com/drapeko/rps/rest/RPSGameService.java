package com.drapeko.rps.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;
import com.drapeko.rps.manager.GameManager;
import com.drapeko.rps.manager.OpponentManager;
import com.drapeko.rps.opponent.Human;
import com.drapeko.rps.opponent.Opponent;
import com.drapeko.rps.opponent.Robot;
 
@Component
@Path("/")
public class RPSGameService {
	
	@Autowired
	GameManager gameManager;
	
	@Autowired
	OpponentManager opponentManager;
	
	@Autowired
	ResponseAssembler responseAssembler;
 
	@GET
	@Path("/person/next/{robot}/{decision}")
	@Produces(MediaType.APPLICATION_JSON)
	public DecisionResponse personDecide(@PathParam("robot") final String robotName, @PathParam("decision") final String decision) {
		RockPaperScissors personDecision = RockPaperScissors.fromName(decision);
		
		if (personDecision == null) {
			throw new RuntimeException("Invalid decision");
		}
		
		
		Human<RockPaperScissors> human = opponentManager.getHuman();
		human.decide(personDecision);
		Robot<RockPaperScissors> robot = opponentManager.getRobot(robotName);

		Scores scores = gameManager.getScores(human, robot);

		RockPaperScissors result = gameManager.nextStep(human, robot);
		
		return responseAssembler.assemble(personDecision, robot.getLatestDecision(), result, scores);	
	}
 
	@GET
	@Path("/robot/next/{robot1},{robot2}")
	@Produces(MediaType.APPLICATION_JSON)
	public DecisionResponse robotDecide(@PathParam("robot1") final String robotName1, @PathParam("robot2") final String robotName2) {
		Robot<RockPaperScissors> robot1 = opponentManager.getRobot(robotName1);
		Robot<RockPaperScissors> robot2 = opponentManager.getRobot(robotName2);
		
		RockPaperScissors result = gameManager.nextStep(robot1, robot2);
		Scores scores = gameManager.getScores(robot1, robot2);
		
		return responseAssembler.assemble(robot1.getLatestDecision(), robot2.getLatestDecision(), result, scores);	
	}
	
	@GET
	@Path("/nullify/{name1},{name2}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrueResponse nullifyRobots(@PathParam("name1") final String name1, @PathParam("name2") final String name2) {
		Opponent<RockPaperScissors> opponent1 = opponentManager.getHumanOrRobot(name1);
		Robot<RockPaperScissors> robot2 = opponentManager.getRobot(name2);
		
		gameManager.nullifyGame(opponent1, robot2);
		return responseAssembler.assemble(true);
	}
	
	
	@GET
	@Path("/scores/{name1},{name2}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScoresResponse getScores(@PathParam("name1") final String name1, @PathParam("name2") final String name2) {
		Opponent<RockPaperScissors> opponent1 = opponentManager.getHumanOrRobot(name1);
		Robot<RockPaperScissors> robot2 = opponentManager.getRobot(name2);
		
		Scores scores = gameManager.getScores(opponent1, robot2);
		
		return responseAssembler.assemble(scores);	
	}
	

}