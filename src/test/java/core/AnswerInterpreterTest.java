package core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import core.model.Form;
import core.model.FormItem;
import core.model.Interview;
import core.model.State;

public class AnswerInterpreterTest {
	Parser parser;
	State stateForMicroTest;

	@Before
	public void init() {
		parser = new Parser();
		stateForMicroTest = new State();
		Map<String, String> transitionMap = new LinkedHashMap<>();
		stateForMicroTest.setTransition(transitionMap);

		Form form = new Form();
		List<FormItem> formItems = new ArrayList<>();
		FormItem formItem = new FormItem();

		formItems.add(formItem);
		form.setFormItems(formItems);
		stateForMicroTest.setForm(form);

	}

	@Test
	public void findNextStateWhenFirstConditionIsTrue() throws NextStateNotFoundException {
		String filePath = "data/game_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		State state = interview.getStates().get(0);
		state.getForm().getFormItems().get(0).setAnswer("warcraft");

		state.getTransition().clear();
		state.getTransition().put("equals[warcraft]", "step3");

		String nextState = AnswerInterpreter.findNextState(state);
		assertEquals("step3", nextState);

	}

	@Test
	public void findNextStateWhenSecondConditionIsTrue() throws NextStateNotFoundException {
		String filePath = "data/game_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		State state = interview.getStates().get(0);
		state.getForm().getFormItems().get(0).setAnswer("star craft");

		String nextState = AnswerInterpreter.findNextState(state);
		assertEquals("step2", nextState);

	}

	@Test
	public void findNextStateWhenDefaultConditionIsGiven() throws NextStateNotFoundException {
		String filePath = "data/game_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		State state = interview.getStates().get(0);
		// no input was given
		// state.getForm().getFormItems().get(0).setAnswer("star craft");

		AnswerInterpreter.findNextState(state);

	}

	@Test(expected = NextStateNotFoundException.class)
	public void findNextStateWhenDefaultConditionIsNotGiven() throws NextStateNotFoundException {
		String filePath = "data/game_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		State state = interview.getStates().get(0);
		// no input was given
		// state.getForm().getFormItems().get(0).setAnswer("star craft");

		// default condition was not given
		interview.getStates().get(0).getTransition().remove("default");

		AnswerInterpreter.findNextState(state);
	}

	@Test
	public void validateStringInputNotNull() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("not_null", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("some input");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateStringInputEqual() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("equals[some input]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("some input");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateStringInputContains() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("contains[some input]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("some input more");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateStringInputDefault() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("default", "step2");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateNumericInputGreaterTrue() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put(">[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("12");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test(expected = NextStateNotFoundException.class)
	public void validateNumericInputGreaterFalse() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put(">[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("9");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateNumericInputGreaterEqualTrue() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put(">=[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("10");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test(expected = NextStateNotFoundException.class)
	public void validateNumericInputGreaterEqualFalse() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put(">=[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("9");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateNumericInputLessTrue() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("<[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("9");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test(expected = NextStateNotFoundException.class)
	public void validateNumericInputLessFalse() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("<[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("10");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateNumericInputLessEqualTrue() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("<=[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("10");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test(expected = NextStateNotFoundException.class)
	public void validateNumericInputLessEqualFalse() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("<=[10]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("12");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateInListInputEqualsAny() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("equalsAny[it, should, be, one, of, us]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("should");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

	@Test
	public void validateInListInputContainsAny() throws NextStateNotFoundException {
		stateForMicroTest.getTransition().clear();
		stateForMicroTest.getTransition().put("containsAny[it, should, contain, one, of, us]", "step2");

		stateForMicroTest.getForm().getFormItems().get(0).setAnswer("I contain one of them");

		String nextState = AnswerInterpreter.findNextState(stateForMicroTest);
		assertEquals("step2", nextState);

	}

}