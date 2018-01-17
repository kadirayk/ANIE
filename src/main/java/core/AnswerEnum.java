package core;

import java.util.List;

public enum AnswerEnum {

	NOT_NULL("not_null"), EQUALS("equals"), EQUALS_ANY("equalsAny"), CONTAINS("contains"), CONTAINS_ANY(
			"containsAny"), GREATER(">"), LESS("<"), GREATER_EQUAL(">="), LESS_EQUAL("<="), DEFAULT("default");

	private String value;

	AnswerEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public boolean validateInList(String input, List<String> expectedList) {
		if (this != DEFAULT && input == null) {
			return false;
		}
		if (expectedList == null) {
			return false;
		}
		switch (this) {
		case CONTAINS_ANY:
			for (String s : expectedList) {
				s = s.trim();
				if (input.contains(s)) {
					return true;
				}
			}
			break;
		case EQUALS_ANY:
			for (String s : expectedList) {
				s = s.trim();
				if (input.equals(s)) {
					return true;
				}
			}
			break;
		case NOT_NULL:
			return input != null;
		case DEFAULT:
			return true;
		default:
			break;

		}
		return false;
	}

	public boolean validateNumeric(Double input, Double expected) {
		if (this != DEFAULT && input == null) {
			return false;
		}
		switch (this) {
		case EQUALS:
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
			return input != null;
		case DEFAULT:
			return true;
		default:
			break;

		}
		return false;
	}

	public boolean validateString(String input, String expected) {
		if (this != DEFAULT && input == null) {
			return false;
		}
		switch (this) {
		case CONTAINS:
			return input.contains(expected);
		case EQUALS:
			return input.equals(expected);
		case NOT_NULL:
			return input != null && !input.isEmpty();
		case DEFAULT:
			return true;
		default:
			break;
		}
		return false;
	}

}
