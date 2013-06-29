package com.drapeko.rps.resolution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.drapeko.rps.choice.RockPaperScissors;

@RunWith(Parameterized.class)
public class TestRPSDecisionMakerBeatsChoice {

	private RockPaperScissors choice;
	private RockPaperScissors beatsChoice;
	private RPSDecisionMaker decisionMaker = new RPSDecisionMaker();

	
	@Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
    		{RockPaperScissors.PAPER, RockPaperScissors.SCISSORS},
    		{RockPaperScissors.SCISSORS, RockPaperScissors.ROCK},
    		{RockPaperScissors.ROCK, RockPaperScissors.PAPER},
        });
    }
    
    public TestRPSDecisionMakerBeatsChoice(RockPaperScissors choice, RockPaperScissors beatsChoice) {
    	this.choice = choice;
    	this.beatsChoice = beatsChoice;
    }
    
    @Test
    public void testBeatsChoice() {
    	assertEquals(beatsChoice, decisionMaker.getChoiceThatBeats(choice));
    }

}
