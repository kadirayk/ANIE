package core.model;

import core.AnswerEnum;

public class Rule {

	AnswerEnum comparator;
	String valueToCompare;

	public Rule(AnswerEnum comparator, String valueToCompare) {
		this.comparator = comparator;
		this.valueToCompare = valueToCompare;
	}

	public AnswerEnum getComparator() {
		return comparator;
	}

	public String getValueToCompare() {
		return valueToCompare;
	}

}
