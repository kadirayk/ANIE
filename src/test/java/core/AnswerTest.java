package core;

import org.junit.Before;
import org.junit.Test;

import core.model.Interview;
import core.model.State;

public class AnswerTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void findNextStateTest() {
		String filePath = "data/game_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		State state = interview.getStates().get(0);
		state.getForm().getFormItems().get(0).setAnswer("warcraft");

		String nextState = new AnswerInterpreter().findNextState(state);

	}
}