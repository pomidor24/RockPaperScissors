package com.drapeko.rps.brain;

import java.util.Map;

import com.drapeko.rps.choice.Choice;

/**
 * Brain remembers last choice and if lost or tied, 
 * increases proportionally weight of winning option
 * 
 * @author romandrapeko
 *
 * @param <T>
 */
public class LearningWeightedBrain<T extends Choice> extends WeightedBrain<T> {

	public LearningWeightedBrain(Map<T, Integer> weightedMap) {
		super(weightedMap);
	}
	
	@Override
	public void handleResult(T me, T opponent, T res) {
		if (res == null) {
			return;
		}
		
		if (opponent.equals(res)) {
			int weightsSum = getWeightsSum();
			int currWeight = weightedMap.get(opponent);
			int increaseBy = Math.max(1, (int) Math.ceil((double)currWeight / weightsSum));
			weightedMap.put(opponent, currWeight + increaseBy);
		}
		
	}
}
