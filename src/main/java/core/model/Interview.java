package core.model;

public class Interview {

	private String context;
	private State[] states;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public State[] getStates() {
		return states;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

}
