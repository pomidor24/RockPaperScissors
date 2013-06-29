package com.drapeko.rps.brain;

import java.util.Map;

import com.drapeko.rps.choice.Choice;
import com.drapeko.rps.util.Utils;


public class WeightedBrain<T extends Choice> extends Brain<T> {
	
	protected Map<T, Integer> weightedMap;
	
	public WeightedBrain(Map<T, Integer> weightedMap) {
		this.weightedMap = weightedMap;
	}
	
	protected int getWeightsSum() {
		int sum = 0;
		
		for (Map.Entry<T, Integer> record : weightedMap.entrySet()) {
			sum += record.getValue();
		}
		
		return sum;
	}
	
	@Override
	public T thinkAndDecide() {
		T result = null;
		
		int sum = getWeightsSum();
		
		int random = Utils.getRandom(0, sum-1);
		
		int currSum = 0;
		for (Map.Entry<T, Integer> record : weightedMap.entrySet()) {
			currSum += record.getValue();
			if (random < currSum) {
				result = record.getKey();
				break;
			}
		}
		previousChoice = latestChoice;
		latestChoice = result;
		return result;
	}

	@Override
	public void handleResult(T me, T opponent, T res) {

	}


}
