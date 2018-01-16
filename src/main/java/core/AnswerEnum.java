package core;

import java.util.List;

public enum AnswerEnum {

	NOT_NULL("not_null"), EQUAL("equal"), CONTAINS("contains"), GREATER(">"), LESS("<"), GREATER_EQUAL(
			">="), LESS_EQUAL("<=");

	private String value;

	AnswerEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public boolean validateInList(String input, List<String> expectedList) {
		if (input == null) {
			return false;
		}
		switch (this) {
		case CONTAINS:
			return expectedList != null && expectedList.contains(input);
		case NOT_NULL:
			break;
		default:
			break;

		}
		return false;
	}

	public boolean validateNumeric(Double input, Double expected) {
		if (input == null) {
			return false;
		}
		switch (this) {
		case EQUAL:
			return input.equals(expected);
		case GREATER:
			return input > expected;
		case GREATER_EQUAL:
			return input >= expected;
		case LESS:
			return input < expected;
		case LESS_EQUAL:
			return input <= expected;
		case NOT_NULL:
			break;
		default:
			break;

		}
		return false;
	}

	public boolean validateString(String input, String expected) {
		if (input == null) {
			return false;
		}
		switch (this) {
		case CONTAINS:
			return input.contains(expected);
		case EQUAL:
			return input.equals(expected);
		case NOT_NULL:
			return !input.isEmpty();
		default:
			break;
		}
		return false;
	}

}
