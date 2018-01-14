package core.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import core.Parser;

public class InterviewTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void nextStateTest() {
		String filePath = "data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		String currentState = interview.getCurrentState().getName();

		assertEquals(currentState, "step1");

		interview.nextState();
		currentState = interview.getCurrentState().getName();
		assertEquals(currentState, "step2");

	}

	@Test
	public void prevStateTest() {
		String filePath = "data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		String currentState = null;

		interview.nextState();
		currentState = interview.getCurrentState().getName();
		assertEquals(currentState, "step2");

		interview.prevState();
		currentState = interview.getCurrentState().getName();
		assertEquals(currentState, "step1");

	}

}
