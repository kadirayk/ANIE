package core.model;

import core.AnswerEnum;
import core.RuleTypeEnum;

public class Rule {

	AnswerEnum comparator;
	String valueToCompare;
	RuleTypeEnum ruleType;

	public Rule(AnswerEnum comparator, String valueToCompare, RuleTypeEnum ruleType) {
		this.comparator = comparator;
		this.valueToCompare = valueToCompare;
		this.ruleType = ruleType;
	}

	public AnswerEnum getComparator() {
		return comparator;
	}

	public String getValueToCompare() {
		return valueToCompare;
	}

	public RuleTypeEnum getRuleType() {
		return ruleType;
	}

}
