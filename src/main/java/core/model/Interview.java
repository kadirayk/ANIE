package core.model;

import java.util.List;

public class Interview {

	private String context;
	private List<State> states;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public String toHTML() {
		StringBuilder html = new StringBuilder();
		for (State s : states) {
			html.append(s.toHTML()).append("\n");
		}
		return html.toString();
	}

}
