package model;

import java.util.ArrayList;

public class Market {

	private ArrayList<State> states;
	private char type;
	private String name;
	
	public Market(ArrayList<State> states, String name) {
		this.states = states;
		this.type = name.charAt(0);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<State> getStates() {
		return states;
	}
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
//		return "Market [states=" + states + ", type=" + type + ", name=" + name + "]";
		return states.size()+"";
	}
	
	
	

}
