
public class Mercado {

	private State states[];
	private char type;
	
	public Mercado(State[] states, char type) {
		this.states = states;
		this.type = type;
	}
	public State[] getStates() {
		return states;
	}
	public void setStates(State[] states) {
		this.states = states;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	

}
