package core;

import java.util.Map;

import core.model.Rule;
import core.model.State;

public class AnswerInterpreter {

	public String findNextState(State state) {
		Map<String, String> transition = state.getTransition();
		for (Map.Entry<String, String> e : transition.entrySet()) {
			String expected = e.getKey();
			String input = state.getForm().getFormItems().get(0).getAnswer();
			if (interprete(input, expected)) {
				return e.getValue();
			}
		}

		return null;

	}

	private boolean interprete(String input, String expected) {
		if (input == null || expected == null) {
			return false;
		}

		Rule rule = extractRule(expected);
		if (rule != null) {
			return rule.getComparator().validateString(input, rule.getValueToCompare());
		}
		return false;
	}

	private Rule extractRule(String expected) {
		if (expected.equals(AnswerEnum.NOT_NULL.value())) {
			return new Rule(AnswerEnum.NOT_NULL, null);
		} else if (expected.startsWith(AnswerEnum.CONTAINS.value())) {
			int start = expected.indexOf('[') + 1;
			int end = expected.indexOf(']');
			String value = expected.substring(start, end);
			return new Rule(AnswerEnum.CONTAINS, value);
		} else if (expected.startsWith(AnswerEnum.EQUAL.value())) {
			int start = expected.indexOf('[') + 1;
			int end = expected.indexOf(']');
			String value = expected.substring(start, end);
			return new Rule(AnswerEnum.EQUAL, value);
		}
		return null;
	}

}
