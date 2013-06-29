package com.drapeko.rps.brain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.drapeko.rps.choice.Choice;
import com.drapeko.rps.resolution.DecisionMaker;
import com.drapeko.rps.util.LimitedQueue;
import com.drapeko.rps.util.Utils;

/**
 * This brain is based on Shannon future teller algortihm. The idea is to track all possible sequences
 * and to update the weights of the sequences once the decision is known
 * 
 * @author drapeko
 *
 * @param <T>
 */
public class ShannonBrain<T extends Choice> extends Brain<T> {
	
	protected static final String KEY_DELIMITER = "_";
	protected static final String KEY_NULL = "N";

	
	/**
	 * Sequence of choices of length movesMemoryLength*2; 
	 * even is me, odd is opponent; 
	 */
	protected LimitedQueue<T> sequence;
	
	/**
	 * All possible sequences indexed by me_opponent;me_opponent;...;me_opponent
	 * Last value is the 'the result' of the sequence
	 */
	protected Map<String, Integer> sequenceMap = new HashMap<String, Integer>();
	
	/**
	 * All possible choices
	 */
	protected Map<Integer, T> choicesMap = new HashMap<Integer, T>();
	
	/**
	 * Decision making logic
	 */
	protected DecisionMaker<T> decisionMaker;

	public ShannonBrain(DecisionMaker<T> decisionMaker, int movesMemoryLength, T...choices) {
		this.decisionMaker = decisionMaker;
		
		int sequenceSize = movesMemoryLength*2;
		
		for (T choice : choices) {
			this.choicesMap.put(choice.getId(), choice);
		}
		
		sequence = new LimitedQueue<T>(sequenceSize);
		for (int i = 0; i < sequenceSize; i++) {
			sequence.add(null);
		}
		
		List<T> choicesAndNull = new ArrayList<T>(Arrays.asList(choices));
		choicesAndNull.add(null);
		
		List<List<T>> sequences = Utils.getAllCombinations(sequenceSize, choicesAndNull);
		prepareSequenceMap(sequences);
	}
	
	/**
	 * Generate key for sequence map
	 * @param sequence
	 * @return
	 */
	private String getKey(List<T> sequence) {
		String res = "";
		for (int i = 0; i < sequence.size(); i++) {
			T elm = sequence.get(i);
			String id = (elm == null) ? KEY_NULL : ""+elm.getId();
			res += id;
			if (i != sequence.size()-1) {
				res += KEY_DELIMITER;
			}
		}
		
		return res;
	}

	/**
	 * Prepares sequence map
	 * @param sequences
	 */
	private void prepareSequenceMap(List<List<T>> sequences) {
		for (List<T> sequence : sequences) {
			String key = getKey(sequence);
			sequenceMap.put(key, 0);			
		}
	}
	
	/**
	 * Returns all matching sequences for your choice and opponent choice
	 * 
	 * @return
	 */
	protected Set<String> getAllMatchingSequenceKeys() {
		Set<List<T>> matchingSequences = Utils.getAllPermutatedReplacedLists(sequence, null);
		
		Set<String> keys = new HashSet<String>();
		for(List<T> seq : matchingSequences) {
			keys.add(getKey(seq));
		}
		
		return keys;
	}
	
	/**
	 * Returns all candidates for think and decide
	 * @return
	 */
	protected Set<String> getAllMatchingCandidates() {
		List<T> list = new ArrayList<T>(sequence);
		list.remove(0);
		list.remove(1);
		list.add(latestChoice);
		
		Set<List<T>> matchingSequences = Utils.getAllPermutatedReplacedLists(list, null);
		
		Set<String> keys = new HashSet<String>();
		for(List<T> seq : matchingSequences) {
			for (Map.Entry<Integer, T> rec : choicesMap.entrySet()) {
				List<T> candidate = new ArrayList<T>(seq);
				candidate.add(rec.getValue());
				keys.add(getKey(candidate));
			}
		}
		
		return keys;		
	}
	
	protected Map<T, Integer> getCandidateWeights() {
		Map<T, Integer> map = new HashMap<T, Integer>();
		
		for (Map.Entry<Integer, T> record : choicesMap.entrySet()) {
			map.put(record.getValue(), 0);
		}
		
		Set<String> keys = getAllMatchingCandidates();
		for (String key : keys) {
			
			String [] parts = key.split(KEY_DELIMITER);

			String result = parts[parts.length-1];
			
			if (!result.equals(KEY_NULL)) {
				int choiceId = Integer.parseInt(result);
				T choice = choicesMap.get(choiceId);
				map.put(choice, map.get(choice) + sequenceMap.get(key));
			}
		}
		
		return map;
	}
	
	@Override
	public T thinkAndDecide() {
		Map<T, Integer> map = getCandidateWeights();
		
		int max = -1;
		List<T> maxChoices = new ArrayList<T>();
		

		for (Map.Entry<T, Integer> record : map.entrySet()) {
			if (max < record.getValue()) {
				maxChoices = new ArrayList<T>();
				maxChoices.add(record.getKey());
				max = record.getValue();
			} else if (max == record.getValue()) {
				maxChoices.add(record.getKey());
			}
		}
		
		T expectedChoice = maxChoices.get(Utils.getRandom(0, maxChoices.size()-1));
		
		previousChoice = latestChoice;
		latestChoice = decisionMaker.getChoiceThatBeats(expectedChoice);
		
		return latestChoice;
	}

	@Override
	public void handleResult(T me, T opponent, T res) {
		sequence.add(previousChoice);
		sequence.add(opponent);
		
		Set<String> keys = getAllMatchingSequenceKeys();
		for (String key : keys) {
			int weight = sequenceMap.get(key);
			sequenceMap.put(key, weight+1);
		}
	}

}
