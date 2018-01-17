package core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.AnswerInterpreter;
import core.NextStateNotFoundException;
import util.ListUtil;

public class Interview {

	private String context;
	private String formRepo;
	private List<State> states;
	private Map<String, State> stateMap;
	private State currentState;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFormRepo() {
		return formRepo;
	}

	public void setFormRepo(String formRepo) {
		this.formRepo = formRepo;
	}

	public State getCurrentState() {
		return currentState;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
		if (ListUtil.isNotEmpty(states)) {
			currentState = states.get(0);
			stateMap = new HashMap<>();
			for (State s : states) {
				stateMap.put(s.getName(), s);
			}
		}

	}

	public void nextState() throws NextStateNotFoundException {
		String nextStateName = AnswerInterpreter.findNextState(currentState);
		if (nextStateName != null) {
			currentState = stateMap.get(nextStateName);
		} // else there is no next step i.e. last step
	}

	public void prevState() {
		String nextStateName = stateMap.get(currentState.getName()).getTransition().get("prev");
		if (nextStateName != null) {
			currentState = stateMap.get(nextStateName);
		} // else there is no next step i.e. last step
	}

	public Map<String, State> getStateMap() {
		return stateMap;
	}

	public String toHTML() {
		StringBuilder html = new StringBuilder();
		for (State s : states) {
			html.append(s.toHTML()).append("\n");
		}
		return html.toString();
	}

}
