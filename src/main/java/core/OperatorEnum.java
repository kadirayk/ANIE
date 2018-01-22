package core;

public enum OperatorEnum {

	EQUAL("="), AND("&"), OR("|"), LEFT_P("("), RIGHT_P(")");

	private String value;

	OperatorEnum(String value) {
		this.value = value;
	}

	public static OperatorEnum findByValue(String value) {
		for (OperatorEnum e : values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}

	public int precedence() {
		switch (this) {
		case EQUAL:
			return 2;
		default:
			return 1;
		}
	}

	public String value() {
		return value;
	}

}
