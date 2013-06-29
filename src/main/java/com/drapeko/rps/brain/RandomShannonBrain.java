package com.drapeko.rps.brain;

import java.util.Map;

import com.drapeko.rps.choice.Choice;
import com.drapeko.rps.resolution.DecisionMaker;
import com.drapeko.rps.util.Utils;

public class RandomShannonBrain<T extends Choice> extends ShannonBrain<T> {

	public RandomShannonBrain(DecisionMaker<T> decisionMaker, int movesMemoryLength, T[] choices) {
		super(decisionMaker, movesMemoryLength, choices);
	}

	@Override
	public T thinkAndDecide() {
		T result = null;
		
		Map<T, Integer> map = getCandidateWeights();
		
		int sum = 0;
		
		for (Map.Entry<T, Integer> record : map.entrySet()) {
			sum += record.getValue();
		}
		
		int random = Utils.getRandom(0, sum-1);
		
		int currSum = 0;
		for (Map.Entry<T, Integer> record : map.entrySet()) {
			currSum += record.getValue();
			if (random <= currSum) {
				result = decisionMaker.getChoiceThatBeats(record.getKey());
				break;
			}
		}
		
		previousChoice = latestChoice;
		latestChoice = result;
		
		return result;
	}
}
