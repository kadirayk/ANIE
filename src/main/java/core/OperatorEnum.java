package core;

public enum OperatorEnum {

	EQUAL("="), AND("&"), OR("|"), LEFT_P("("), RIGHT_P(")");

	private String value;

	OperatorEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
