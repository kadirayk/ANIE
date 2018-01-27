package core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import core.model.Interview;
import core.model.Question;
import core.model.Rule;
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
				content = content.replace(s, q.getAnswer());
			} else if (content.contains(s.replace(interview.getCurrentState().getName() + ".", ""))) {
				Question q = interview.getQuestionByPath(s);
				String answer = q.getAnswer() == null ? "null" : q.getAnswer();
				content = content.replace(s.replace(interview.getCurrentState().getName() + ".", ""), answer);
			}
		}

		ExpressionEvaluator ev = new ExpressionEvaluator(content);
		boolean result = ev.evaluateExpression();
		return result;

	}

	// public static String findNextState(State state) throws
	// NextStateNotFoundException {
	// Map<String, String> transition = state.getTransition();
	// for (Map.Entry<String, String> e : transition.entrySet()) {
	// String condition = e.getKey();
	// String input = state.getQuestions().get(0).getAnswer();
	// if (interprete(input, condition)) {
	// return e.getValue();
	// }
	// }
	// throw new NextStateNotFoundException();
	//
	// }

	private static boolean interprete(String input, String condition) {
		if (condition == null) {
			return false;
		}

		Rule rule = extractRuleNew(condition);
		if (rule != null) {
			if (rule.getRuleType() == RuleTypeEnum.STRING) {
				return rule.getComparator().validateString(input, rule.getValueToCompare());
			} else if (rule.getRuleType() == RuleTypeEnum.NUMERIC) {
				checkForNumericValue(input);
				Double inputDouble = Double.parseDouble(input);
				Double valueToCompareDouble = Double.parseDouble(rule.getValueToCompare());
				return rule.getComparator().validateNumeric(inputDouble, valueToCompareDouble);
			} else if (rule.getRuleType() == RuleTypeEnum.LIST) {
				List<String> valueList = Arrays.asList(rule.getValueToCompare().trim().split(","));
				return rule.getComparator().validateInList(input, valueList);
			}

		}
		return false;
	}

	private static Rule extractRuleNew(String condition) {
		if (condition == null) {
			return null;
		}

		if (condition.equals(AnswerEnum.NOT_NULL.value())) {

			return new Rule(AnswerEnum.NOT_NULL, null, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.CONTAINS_ANY.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.CONTAINS_ANY, value, RuleTypeEnum.LIST);

		} else if (condition.startsWith(AnswerEnum.EQUALS_ANY.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.EQUALS_ANY, value, RuleTypeEnum.LIST);

		} else if (condition.startsWith(AnswerEnum.CONTAINS.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.CONTAINS, value, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.EQUALS.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.EQUALS, value, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.GREATER_EQUAL.value())) {

			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.GREATER_EQUAL, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.GREATER.value())) {

			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.GREATER, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.LESS_EQUAL.value())) {
			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.LESS_EQUAL, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.LESS.value())) {
			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.LESS, value, RuleTypeEnum.NUMERIC);
		} else if (condition.equals(AnswerEnum.DEFAULT.value())) {
			return new Rule(AnswerEnum.DEFAULT, null, RuleTypeEnum.STRING);
		}
		return null;
	}

	/**
	 * 
	 * @param condition
	 *            is in the format i.e. equal["str"], not_null, default
	 * @return
	 */
	private static Rule extractRule(String condition) {
		if (condition.equals(AnswerEnum.NOT_NULL.value())) {

			return new Rule(AnswerEnum.NOT_NULL, null, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.CONTAINS_ANY.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.CONTAINS_ANY, value, RuleTypeEnum.LIST);

		} else if (condition.startsWith(AnswerEnum.EQUALS_ANY.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.EQUALS_ANY, value, RuleTypeEnum.LIST);

		} else if (condition.startsWith(AnswerEnum.CONTAINS.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.CONTAINS, value, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.EQUALS.value())) {

			String value = getConditionContent(condition);

			return new Rule(AnswerEnum.EQUALS, value, RuleTypeEnum.STRING);

		} else if (condition.startsWith(AnswerEnum.GREATER_EQUAL.value())) {

			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.GREATER_EQUAL, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.GREATER.value())) {

			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.GREATER, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.LESS_EQUAL.value())) {
			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.LESS_EQUAL, value, RuleTypeEnum.NUMERIC);

		} else if (condition.startsWith(AnswerEnum.LESS.value())) {
			String value = getConditionContent(condition);
			checkForNumericValue(value);

			return new Rule(AnswerEnum.LESS, value, RuleTypeEnum.NUMERIC);
		} else if (condition.equals(AnswerEnum.DEFAULT.value())) {
			return new Rule(AnswerEnum.DEFAULT, null, RuleTypeEnum.STRING);
		}
		return null;
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

	private static void checkForNumericValue(String value) {
		if (!StringUtils.isNumeric(value)) {
			throw new IllegalArgumentException("A numeric value is expected");
		}
	}

}
