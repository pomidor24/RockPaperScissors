package com.drapeko.rps.choice;

public enum RockPaperScissors implements Choice {
	ROCK(0, "Rock"),
	PAPER(1, "Paper"),
	SCISSORS(2, "Scissors");
	
	private int id;
	private String name;
	
	RockPaperScissors(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
    public int getId() {
        return this.id;
    }
    
    public String getName() {
    	return this.name;
    }

    public static RockPaperScissors fromName(String name) {
        for (RockPaperScissors e : RockPaperScissors.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
    
    public static RockPaperScissors fromId(int id) {
        for (RockPaperScissors e : RockPaperScissors.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
    	return id + " " + name;
    }
}
