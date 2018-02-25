package core;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import core.model.Interview;
import core.model.Question;
import core.model.State;

public class AnswerInterpreter {

	private AnswerInterpreter() {
	}

	public static String findNextState(Interview interview, State state) throws NextStateNotFoundException {
		Map<String, String> transition = state.getTransition();
		for (Map.Entry<String, String> e : transition.entrySet()) {
			String condition = e.getKey();
			if (evaluate(interview, condition)) {
				return e.getValue();
			}
		}
		throw new NextStateNotFoundException();

	}

	private static boolean evaluate(Interview interview, String condition) {
		if (condition.equals("default")) {
			return true;
		}
		String content = getConditionContent(condition);
		for (String s : interview.getQuestionSet()) {
			if (content.contains(s)) {
				Question q = interview.getQuestionByPath(s);
				if (StringUtils.isEmpty(q.getAnswer())) {
					return false;
				}
				content = content.replace(s, q.getAnswer());
			} else if (content.contains(s.replace(interview.getCurrentState().getName() + ".", ""))) {
				Question q = interview.getQuestionByPath(s);
				if (StringUtils.isEmpty(q.getAnswer())) {
					return false;
				}
				String answer = q.getAnswer() == null ? "null" : q.getAnswer();
				content = content.replace(s.replace(interview.getCurrentState().getName() + ".", ""), answer);
			}
		}

		ExpressionEvaluator ev = new ExpressionEvaluator(content);
		return ev.evaluateExpression();

	}

	private static String getConditionContent(String condition) {
		int start = getStartIndexOfValue(condition);
		int end = getEndIndexOfValue(condition);
		return condition.substring(start, end);
	}

	private static int getEndIndexOfValue(String condition) {
		int index = condition.indexOf(']');
		if (index == -1) {
			throw new IllegalArgumentException(
					"Closing bracket \"]\" is missing. This condition must contain a value to compare within brackets. i.e [value]");
		}
		return index;
	}

	private static int getStartIndexOfValue(String condition) {
		int index = condition.indexOf('[');
		if (index == -1) {
			throw new IllegalArgumentException(
					"Opening bracket \"[\" is missing. This condition must contain a value to compare within brackets. i.e [value]");
		}
		return index + 1;
	}

}
