package com.drapeko.rps.manager;

import com.drapeko.rps.choice.RockPaperScissors;
import com.drapeko.rps.game.Scores;
import com.drapeko.rps.opponent.Opponent;

public interface GameManager {

	RockPaperScissors nextStep(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2);

	Scores getScores(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2);

	void nullifyGame(Opponent<RockPaperScissors> opponent1, Opponent<RockPaperScissors> opponent2);
}
